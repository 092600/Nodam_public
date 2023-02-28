from bs4 import BeautifulSoup
import requests
import json

import pymysql
import datetime

conn = pymysql.connect(host='localhost',
                             user='MySQL_username',
                             password='MySQL_password!',
                             database='DB')

cursor = conn.cursor()
select_sql = "SELECT count(*) FROM no_smoke_try_people"
cursor.execute(select_sql)
result = cursor.fetchone()[0]

cursor2 = conn.cursor()

today = datetime.datetime.today().strftime("%Y-%m-%d")    
insert_sql = "INSERT INTO no_smoke_try_people (date, amount_of_quit_people, amount_of_try_people) VALUES (%s, %s, %s);"
url = f'https://nosmk.khealth.or.kr/nsk/ntcc/index.do'

resp = requests.get(url)
soup = BeautifulSoup(resp.text,'html.parser')
nc_count = soup.find('section',class_="nc-count")
nc_count_dict = dict()
count_list = list()

for msg in nc_count.find_all('p'):
    msg = msg.text.strip().replace('명','')
    if msg.isalpha() == False :
        count_list.append(msg)
nc_count_dict['count1'] = count_list[0]
nc_count_dict['count2'] = count_list[1]


print(today)
val = (today, nc_count_dict['count2'], nc_count_dict['count1'])
cursor.execute(insert_sql, val)
conn.commit()


