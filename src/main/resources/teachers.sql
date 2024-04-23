create table teachers (
	reg_number VARCHAR(6),
	full_name VARCHAR(50) not null,
	email VARCHAR(100) not null,
	course_in_charge VARCHAR(6) unique not null references courses (code),
	constraint teachers_pkey PRIMARY KEY (reg_number)
);

insert into teachers (reg_number, full_name, email, course_in_charge) values ('M0/S04', 'Lazaro Dorian', 'ldorian0@smh.com.au', 'CHM101');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('IT/S03', 'Jane Molineux', 'jmolineux1@discovery.com', 'CHM105');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('UI/S62', 'Elwira Danaher', 'edanaher2@java.com', 'CVE201');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('UX/S84', 'Kasey Fanton', 'kfanton3@usatoday.com', 'CVE204');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('WZ/S32', 'Joan Courtier', 'jcourtier4@google.co.jp', 'EEE201');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('CN/S52', 'Eric Vockins', 'evockins5@nyu.edu', 'EEE309');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('C7/S51', 'Terrance Petch', 'tpetch6@amazon.co.uk', 'EEE313');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('VH/S98', 'Giffie Dickerline', 'gdickerline7@ovh.net', 'GST102');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('OL/S71', 'Ollie Lamberts', 'olamberts8@flavors.me', 'GST103');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('HX/S03', 'Randa Quaintance', 'rquaintance9@businessweek.com', 'GST223');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('WL/S35', 'Emelda Watford', 'ewatforda@youtu.be', 'MEE204');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('E6/S24', 'Kalli Benck', 'kbenckb@jimdo.com', 'MEE206');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('JC/S76', 'Karin Denson', 'kdensonc@hostgator.com', 'MEE207');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('PE/S31', 'Arlie Orteu', 'aorteud@vimeo.com', 'MEE208');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('GZ/S74', 'Drusie Pitceathly', 'dpitceathlye@delicious.com', 'MEE304');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('BA/S02', 'Bambie Treneer', 'btreneerf@telegraph.co.uk', 'MEE309');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('QZ/S26', 'Colman Florez', 'cflorezg@woothemes.com', 'MEE312');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('MD/S62', 'Virginia Boyson', 'vboysonh@typepad.com', 'MEE313');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('K1/S11', 'Shana FitzAlan', 'sfitzalani@scribd.com', 'MEE405');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('BJ/S44', 'Morena Egleton', 'megletonj@nps.gov', 'MEE407');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('W5/S85', 'Orville Waszczykowski', 'owaszczykowskik@cmu.edu', 'MEE411');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('TG/S09', 'Marcela Ucchino', 'mucchinol@telegraph.co.uk', 'MEE413');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('FI/S21', 'Mia Staziker', 'mstazikerm@google.pl', 'MEE415');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('TX/S37', 'Xenia Spottiswood', 'xspottiswoodn@cisco.com', 'MEE508');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('ZG/S77', 'Shaina Leblanc', 'sleblanco@cbc.ca', 'MEE509');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('J4/S98', 'Thorpe Eastridge', 'teastridgep@xing.com', 'MEE510');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('IF/S47', 'Jordan Carmo', 'jcarmoq@microsoft.com', 'MEE512');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('IA/S87', 'Law Southern', 'lsouthernr@oakley.com', 'MEE520');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('RO/S36', 'Sigfrid Rosenstiel', 'srosenstiels@blog.com', 'MTH101');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('OR/S21', 'Gillan O''Keefe', 'gokeefet@fda.gov', 'MTH103');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('QA/S94', 'Junia Farriar', 'jfarriaru@blogs.com', 'MTH202');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('GC/S65', 'Janot Saw', 'jsawv@sphinn.com', 'MTH341');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('D6/S59', 'Vivyan Bonass', 'vbonassw@fotki.com', 'PHY104');
insert into teachers (reg_number, full_name, email, course_in_charge) values ('VK/S90', 'Crichton Ayars', 'cayarsx@networkadvertising.org', 'STA344');