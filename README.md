# Integrated IT Project - SIT Announcement System
A single-page web application that displays information about numerous announcements

## Software development environment

> Front-end (Parts of Users Interaction)
- Create the user interface using `Vue.js` (Organize document structure, display format, and many functionalities as necessary to be in accordance with requirements) and use `TailwindCSS` to adorn the user interface.

<div align="center">
<img src="https://static-00.iconduck.com/assets.00/vue-js-icon-2048x1766-btrgkrhi.png" width="50" height="45"> 
<img src="https://velog.velcdn.com/images/diso592/post/8c07ebac-2637-48b8-ae55-41915e2e30bf/image.png" width="60" height="55">
</div>

> Back-end (Parts of Resource Provider)
- Through defined APIs and methods created using the Java Programming language on the `Spring Boot Framework`, handle requests originating from front-end / client browsers.
<div align="center">
<img src="https://static.vecteezy.com/system/resources/previews/022/101/050/original/java-logo-transparent-free-png.png" width="90" height="85"> 
<img src="https://miro.medium.com/v2/resize:fit:700/0*iD7UPcCzaDrqadk9.png" width="150" height="75">
</div>

> Database (Parts of Stored Data)
- Utilize the SQL language with the `MySQL` software to store data in the form of a relational database (row-column-table).
<div align="center">
<img src="https://aety.io/wp-content/uploads/2016/11/mysql-logo.png" width="120" height="85"> 
</div>

> DevOps (Parts of Deployment)
- Configure the virtual machine powered by Ubuntu and deploy the created software to the faculty server using `Docker`
   <br> and `command line interface`.
<div align="center">
<img src="https://images.crunchbase.com/image/upload/c_lpad,f_auto,q_auto:eco,dpr_1/ywjqppks5ffcnbfjuttq" width="90" height="85"> 
</div>

> Project Management Part
- Daily meetings 4 times a week with team members and 2 meetings with advisors at the beginning and end of each sprint
  <br>via `Microsoft Teams`.
- Utilize TaskBoard Online - `Trello` to view your to-do list and the status of each task extracted from Product Backlog Item.
- Shared Code via `GitHub`.
<div align="center">
<img src="https://download.logo.wine/logo/Microsoft_Teams/Microsoft_Teams-Logo.wine.png" width="130" height="95"> 
  <img src="https://cdn.icon-icons.com/icons2/3041/PNG/512/trello_logo_icon_189227.png" width="90" height="85"> 
  <img src="https://static-00.iconduck.com/assets.00/github-icon-2048x1988-jzvzcf2t.png" width="90" height="85"> 
</div>

## User Interface and Functionality
> Admin Role

1. **`[CRUD Operations] - Announcement List Display`** : ผู้ใช้ที่มี Role `Admin` จะเห็น รายละเอียดเบื้องต้นของตัวประกาศ โดยเรียงลำดับจากประกาศที่เพิ่มาล่าสุดก่อนแล้วลดหลั่นลงไปตามลำดับ ข้อมูลที่แสดงจะประกอบไปด้วย : หัวข้อการประกาศ, ประเภทของการประกาศ, วันเวลาเปิด-ปิดของตัวประกาศ (แสดงวัน-เวลาตาม Timezone บน browser ของผู้ใช้), ตัวเลือกว่าประกาศนี้จะแสดงให้ ผู้ใช้ทั่วไปเห็นหรือไม่ และจำนวนครั้งที่มีการเข้าดูการประกาศนั้นๆจากผู้ใช้ที่มี Role `User` ทั่วไป
<img src="./YuuuHooo-docs/IMG/announcement-list.png" align="center">

2. **`[CRUD Operations] - Add New Announcement`** : ผุ้ใช้ที่มี Role `Admin` สามารถเพิ่มข้อมูลประกาศใหม่ได้ โดยคลิกที่ปุ่ม `Add Announcement` ที่มุมบนขวา
<img src="./YuuuHooo-docs/IMG/add-button.png" align="center">

* เมื่อเข้าสู่หน้า Add announcement ก็สามารถทำการกรอกข้อมูลสำหรับตัว announcement ใหม่ที่ต้องการเพิ่มได้เลย โดยกำหนดไว้ว่าต้องทำการกรอกข้อมูล ส่วนที่เป็น Title , Category , Description ทั้ง 3 ส่วนนี้ก่อนจึงจะกดปุ่ม `submit` เพื่อเป็นการสร้าง announcement ใหม่ได้ หรือ สามารถกดปุ่ม `cancel` ได้เลยถ้าต้องการยกเลิกการทำงาน
<img src="./YuuuHooo-docs/IMG/plain-add-page.png" align="center">
<img src="./YuuuHooo-docs/IMG/fullfield-add-page.png" align="center">

* เมื่อทำการกด `submit` ตัวประกาศก็จะปรากฎขึ้นในหน้า Announcement List
<img src="./YuuuHooo-docs/IMG/after-add-announcementlist.png" align="center">

3. **`[CRUD Operations] - View & Edit Announcement`** : ในหน้าของ Announcement List ผู้ใช้ที่มี Role Admin สามารถคลิกปุ่ม `View` ที่ Announcement ตัวใดตัวหนึ่ง เพื่อดูรายละเอียดทั้งหมดของ Announcement นั้นๆ
<img src="./YuuuHooo-docs/IMG/view-button.png" align="center">
<img src="./YuuuHooo-docs/IMG/detail-page.png" align="center">

* สามารถคลิกปุ่ม Edit เพื่อทำการแก้ไขข้อมูล Announcement ได้ โดยข้อมูลเดิมของตัว Announcement ก็จะถูกเติมลงในช่องว่างพร้อมให้แก้ไขได้เลย กรณีที่ไม่ได้ทำการแก้ไข ข้อมูลไม่มีการเปลี่ยนแปลง จะไม่สามารถคลิกปุ่ม `submit` ได้ แต่เมื่อทำการแก้ไขเรียบร้อย ข้อมูลมีการเปลี่ยนแปลงสามารถคลิกปุ่ม `submit` เพื่อบันทึกการเปลี่ยนแปลงได้ หรือ สามารถคลิกปุ่ม `cancel` เพื่อยกเลิกการทำงานได้

<img src="./YuuuHooo-docs/IMG/edit-announcement.png" align="center">
<img src="./YuuuHooo-docs/IMG/after-edit.png" align="center">

4. **`[CRUD Operations] - Delete Announcement`** : ในหน้าของ Announcement List ผู้ใช้ที่มี Role Admin สามารถคลิกปุ่ม `delete` ที่ Announcement ตัวใดตัวหนึ่ง เพื่อทำการลบตัว announcement นั้นทิ้งได้ โดยจะมี alert เตือนผู้ใช้ก่อนว่าต้องการจะลบจริงหรือไม่ ป้องกันการลบโดยไม่ได้ตั้งใจ ถ้ายืนยันที่จะลบก็คลิกปุ่ม `ok` ตัว announcement นั้นก็จะหายไป ถ้าคลิกปุ่ม `cancel` ก็จะเป็นการยกเลิกการทำงาน
<img src="./YuuuHooo-docs/IMG/delete-announcement.png" align="center">
<img src="./YuuuHooo-docs/IMG/after-delete.png" align="center">

5. **`Timezone`** : แสดงผลวัน-เวลา dynamic ตาม local timezone ที่กำหนดบน browser 
<img src="./YuuuHooo-docs/IMG/default-timezone.png" align="center">
<div align="center">
<span align="center"><i>default timezone (เวลาประเทศไทย)</i></span>
<img src="./YuuuHooo-docs/IMG/change-timezone.png" align="center">
<span align="center"><i>change timezone</i></span>
</div>
