import pymysql

conn = pymysql.connect(host='localhost',
                             user='MySQL_username',
                             password='MySQL_password',
                             database='DB')

cursor = conn.cursor()

with open("clinicInfo_location", "r", encoding="utf-8") as f:
    lines = f.readlines()
    insert_sql = "INSERT INTO clinic (id, region, organization, address, call_number) VALUES (%s, %s, %s, %s, %s);"
    for i in range(len(lines)):
        line = lines[i].split(", ")
        
        val = (i+1, line[0], line[1], line[2], line[3])
        cursor.execute(insert_sql, val)
        conn.commit()
