# -*- coding: utf-8 -*-
import random
familyName = ['Ngyễn','Lê','Bùi','Phạm','Vũ','Võ','Hoàng','Huỳnh','Đỗ ','Phan ','Đặng','Trần','Hồ','Ngô','Dương','Lý']
middleName=['An', 'Bảo', 'Công', 'Duy', 'Gia', 'Hải', 'Hạo', 'Hiếu', 'Hiểu', 'Hoàng', 'Hùng', 'Hương', 'Hữu', 'Huy', 'Lê', 'Minh', 'Ngân', 'Ngọc', 'guyên', 'Phong', 'Phước', 'Quân', 'Quang', 'Quốc', 'Quý', 'Thiên', 'Thiện', 'Tiến', 'Vĩ', 'Vũ', 'Xuân']
givenName=['Phúc', 'Lộc', 'Thọ', 'Đoan', 'Trang', 'Tuyết', 'Trinh', 'Hiền', 'Thương', 'Hùng', 'Dũng', 'Bảo', 'Trân', 'Trọng', 'Châu', ' Kiểm', 'Tùng', 'Tráng', 'Tạc', 'Căn', 'Cương', 'Giang', 'Doanh', 'Sâm', 'Cán', 'Khải']
departments=['CHKT','CNHKVT','CNN','CNNN','CNTT','DTVT','TDH','VLKT' ]
trainingSite=['HN','HCM','DN']

f = open('insert_student_query.sql','w')

f.write('USE schooldb;\n')
f.write('START TRANSACTION;\n')
f2 = open('insert_result_query.sql','w')

f2.write('USE schooldb;\n')
f2.write('START TRANSACTION;\n')
for i in range(17020000,17021000):
    is_regular = random.randrange(0,2)
    name=random.choice(familyName)+" "+random.choice(middleName)+" "+random.choice(middleName)
    bdate=str(random.randrange(1990, 2003))+"-"+str(random.randrange(1, 12))+"-"+str(random.randrange(1, 29))
    adYear = random.randrange(2017,2023)
    grage = random.randrange(25,30)
    department = random.choice(departments)
    if(is_regular):
        f.write("CALL InsertRegularStudent("+str(i)+", \'"+name+"\', "+"\'"+bdate+"\', "+str(adYear)+", "+str(grage)+", \'"+department+"\');\n")
    else:
        site = random.choice(trainingSite)
        f.write("CALL InsertInServiceStudent("+str(i)+", \'"+name+"\', "+"\'"+bdate+"\', "+str(adYear)+", "+str(grage)+", \'"+department+"\', \'"+site+"\');\n")

    for j in range(adYear,adYear+min(2023-adYear+1,4)):
        grade = round(random.uniform(4.0,10.0),1)
        semesterID=j*10+1
        f2.write("CALL InsertStudentResult("+str(i)+", "+str(grade)+", "+str(semesterID)+");\n");
        grade = round(random.uniform(4.0,10.0),1)
        semesterID=j*10+2
        f2.write("CALL InsertStudentResult("+str(i)+", "+str(grade)+", "+str(semesterID)+");\n");


f.write('COMMIT;\n')
f2.write('COMMIT;\n')
f2.close()
f.close()

# for i in range(2012,2024):
#     print("("+str(i*10+1)+", 'First Semester of "+str(i)+"','"+str(i)+"-09-06'),")
#     print("("+str(i*10+2)+", 'Second Semester of "+str(i)+"','"+str(i+1)+"-01-17'),")

