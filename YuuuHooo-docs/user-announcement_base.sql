TRUNCATE TABLE announcements ;

insert into announcements (announcementTitle, announcementDescription,announcementCategory,publishDate,closeDate, announcementDisplay)
	values('(PBI8-9) Title 1','(PBI8-9) Descripition 1',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
		  ('(PBI8-9) Title 2','(PBI8-9) Descripition 2',1,'2023-06-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
		  ('(PBI8-9) Title 3','(PBI8-9) Descripition 3',1,null,'2023-12-31 18:00:00+07:00','Y'),
		  ('(PBI8-9) Title 4','(PBI8-9) Descripition 4',1,null,'2023-05-01 18:00:00+07:00','Y'),
		  ('(PBI8-9) Title 5','(PBI8-9) Descripition 5',2,'2023-05-01 06:00:00+07:00','2023-05-02 18:00:00+07:00','Y'),
		  ('(PBI8-9) Title 6','(PBI8-9) Descripition 6',2,'2023-05-01 06:00:00+07:00',null,'Y'),
		  ('(PBI8-9) Title 7','(PBI8-9) Descripition 7',2,'2023-06-01 06:00:00+07:00',null,'Y'),
		  ('(PBI8-9) Title 8','(PBI8-9) Descripition 8',2,null,null,'Y'),
		  ('(PBI8-9) Title 9','(PBI8-9) Descripition 9',2,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','N') ;