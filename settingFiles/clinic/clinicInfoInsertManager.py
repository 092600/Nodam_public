import pymysql, sys,os

sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

from config import DB_Config as db_config
conn = pymysql.connect(host=db_config.host,
                             user=db_config.user,
                             password=db_config.password,
                             database=db_config.database)

cursor = conn.cursor()

with open("clinic/clinicInfo.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
    insert_sql = "INSERT INTO clinic (id, region, organization, address, call_number) VALUES (%s, %s, %s, %s, %s);"
    for i in range(len(lines)):
        line = lines[i].split(", ")
        
        val = (i+1, line[0], line[1], line[2], line[3])
        cursor.execute(insert_sql, val)
        conn.commit()
