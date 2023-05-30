
-- INSERT Test Data category-base 
insert into categories values
(1, "ทั่วไป"),(2, "ทุนการศึกษา"),(3, "หางาน"),(4, "ฝึกงาน");


-- INSERT Test Data announcement-base 
insert into announcements(idAnnouncement,announcementTitle,announcementDescription,announcementCategory,publishDate,closeDate,announcementDisplay)
VALUES ('1', 'บริษัท เน็ตเซอร์พลัส จำกัด รับสมัครงาน 2 ตำแหน่ง', 'บริษัท เน็ตเซอร์พลัส จำกัด เปิดรับสมัครงาน 2 ตำแหน่ง Application Support และ Customer Support',3,null,null,'N'),
       ('2','รายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" 2/2565','คณะ ฯ ประกาศรายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" ประจำภาคการศึกษา 2/2565',2,null,'2023-05-31 11:00:00','Y'),
       ('3','แนวปฎิบัติการสอบออนไลน์ พ.ศ. 2565','ประกาศมหาวิทยาลัยเทคโนโลยีพระจอมเกล้าธนบุรี เรื่องแนวทางปฎิบัติการสอบออนไลน์ พ.ศ. 2565',1,'2023-01-26 23:00:00',null,'Y'),
       ('4', 'กิจกรรมพี่อ้อย พี่ฉอด On Tour 2566','ขอเชิญนักศึกษาทุกชั้นปี เข้าร่วมกิจกรรมพี่อ้อย พี่ฉอด On Tour',1,'2023-04-18 23:00:00','2023-05-08 11:00:00','Y');
use announcement;
delete from announcements ;