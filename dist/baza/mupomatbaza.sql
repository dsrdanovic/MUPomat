
drop database if exists mupbaza;
create database mupbaza character set utf8 
collate utf8_general_ci;
use mupbaza;

create table policijskauprava(
	sifra					int not null primary key auto_increment,
	naziv					varchar(50) not null,
	nacelnik				varchar(50) not null,
	sjediste				varchar(50) not null,
	adresa					varchar(50) not null,
	telefon					varchar(50) not null,
	internetstranica                        varchar(50) not null,
	datumunosa 				datetime not null,
	datumpromjene                           datetime not null,
	operater 				int not null
)engine=innodb;

create table grad(
	sifra					int not null primary key auto_increment,
	naziv					varchar(50) not null,
	postanskibroj                           varchar(50) not null,
	policijskauprava                        int not null,
	datumunosa 				datetime not null,
	datumpromjene                           datetime not null,
	operater 				int not null
)engine=innodb;	

create table policijskapostaja(
	sifra					int not null primary key auto_increment,
	naziv					varchar(50) not null,
	nacelnik				varchar(50) not null,
	adresa					varchar(50) not null,	
	telefon					varchar(50) not null,
	slika					varchar (255),
	grad					int not null,
	datumunosa 				datetime not null,
	datumpromjene                           datetime not null,
	operater				int not null
)engine=innodb;	

create table automat(
	sifra					int not null primary key auto_increment,
	naziv					varchar(50) not null,
	proizvodac				varchar(50) not null,
	cijena					decimal(18,2),
	policijskapostaja                       int not null,
	datumunosa 				datetime not null,
	datumpromjene                           datetime not null,
	operater 				int not null
)engine=innodb;	

create table dokument(
	sifra					int not null primary key auto_increment,
	rednibroj                       	int not null,
	naziv           			varchar(50) not null,
	automat					int not null,
	datumunosa 				datetime not null,
	datumpromjene                           datetime not null,
	operater 				int not null
)engine=innodb;	

create table korisnik(
	sifra 					int not null primary key auto_increment,
	oib 					char(13) not null,
	automat					int null,
	datumregistracije                       datetime not null,
	korisnickoime                           varchar(15) not null,
	lozinka					char(32) not null,
	uloga					varchar(50) not null,
	aktivan					boolean not null
)engine=innodb;	

create table osoba(
	oib 					char(13) not null primary key,
	ime					varchar(50) not null,
	prezime 				varchar(50) not null,
	email                                   varchar(100) not null
)engine=innodb;

create table operater(
	sifra 					int not null primary key auto_increment,
	korisnickoime                           varchar(15) not null,
	lozinka					char(32) not null,
	ime 					varchar(50) not null,
	prezime					varchar(50) not null,
	aktivan 				boolean not null
)engine=innodb;

create unique index index_1 on operater(korisnickoime);
	
alter table grad add foreign key (policijskauprava) references policijskauprava(sifra);
alter table policijskapostaja add foreign key (grad) references grad(sifra);
alter table automat add foreign key (policijskapostaja) references policijskapostaja(sifra);
alter table dokument add foreign key (automat) references automat(sifra);
alter table korisnik add foreign key (automat) references automat(sifra);
alter table korisnik add foreign key (oib) references osoba(oib);

-- VANJSKI KLJUČEVI ZA OPERATERA

alter table policijskauprava add foreign key (operater) references operater(sifra);
alter table grad add foreign key (operater) references operater(sifra);
alter table policijskapostaja add foreign key (operater) references operater(sifra);
alter table automat add foreign key (operater) references operater(sifra);
alter table dokument add foreign key (operater) references operater(sifra);

-- OPERATER
#1
insert into operater(korisnickoime,lozinka,ime,prezime,aktivan) values
('admin',md5('admin'),'Dario', 'Srdanović',true);
insert into operater(korisnickoime,lozinka,ime,prezime,aktivan) values
('mario',md5('mario'),'Mario', 'Kušen',true);
insert into operater(korisnickoime,lozinka,ime,prezime,aktivan) values
('pozda',md5('pozda'),'Ivan', 'Pozderac',true);

-- POLICIJSKA UPRAVA
#1
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('ZAGREBAČKA','Goran Burušić','Zagreb','Matice hrvatske 4','01/4563-111','www.zagrebacka.policija.hr',now(),now(),1);
#2
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('SPLITSKO-DALMATINSKA','Marko Srdarević','Split','Trg hrvatske bratske zajednice 9','021/307-111','www.splitsko-dalmatinska.policija.hr',now(),now(),1);
#3
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('PRIMORSKO-GORANSKA','Senka Šubat','Rijeka','Ulica žrtava fašizma 3','051/430-333','www.primorska.policija.hr',now(),now(),1);
#4
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('OSJEČKO-BARANJSKA','Milan Baričević','Osijek','Trg Lavoslava Ružičke 1','031/237-111','www.osjecko-baranjska.policija.hr',now(),now(),1);
#5
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('ISTARSKA','Dragutin Cestar','Pula','Trg Republike 1','052/532-111','www.istarska.policija.hr',now(),now(),1);
#6
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('DUBROVAČKO-NERETVANSKA','Tonći Radibratović','Dubrovnik','Dr. Ante Starčevića 13','020/443-333','www.dubrovacko-neretvanska.policija.hr',now(),now(),1);
#7
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('KARLOVAČKA','Tomislav Kotić','Karlovac','Trg hrvatskih redarstvenika 6','047/664-111','www.karlovacka.policija.hr',now(),now(),1);
#8
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('SISAČKO-MOSLAVAČKA','Marko Rašić','Sisak','Rimska 19','044/560-111','www.sisacko-moslavacka.policija.hr',now(),now(),1);
#9
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('ŠIBENSKO-KNINSKA','Ivica Kostanić','Šibenik','Velimira Škorpika 5','022/347-139','www.sibensko-kninska.policija.hr',now(),now(),1);
#10
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('VUKOVARSKO-SRIJEMSKA','Miroslav Puž','Vinkovci','Glagoljaška 27b','032/342-239','www.vukovarsko-srijemska.policija.hr',now(),now(),1);
#11
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('ZADARSKA','Neven Paškalin','Zadar','Andrije Hebranga bb','023/345-111','www.zadarska.policija.hr',now(),now(),1);
#12
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('BJELOVARSKO-BILOGORSKA','Dragutin Vurnek','Bjelovar','Vlahe Paljetka 2','043/270-111','www.bjelovarsko-bilogorska.policija.hr',now(),now(),1);
#13
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('BRODSKO-POSAVSKA','Ante Zovak','Slavonski Brod','Ivana Mažuranića 9','035/211-111','www.brodsko-posavska.policija.hr',now(),now(),1);
#14
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('KOPRIVNIČKO-KRIŽEVAČKA','Darko Rep','Koprivnica','Trg Eugena Kumičića 18','048/656-411','www.koprivnicko-krizevacka.policija.hr',now(),now(),1);
#15
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('KRAPINSKO-ZAGORSKA','Jakob Bukvić','Zabok','Matije Gupca 53','049/225-111','www.krapinsko-zagorska.policija.hr',now(),now(),1);
#16
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('LIČKO-SENJSKA','Ante Podnar','Gospić','Ulica Hrvatskog sokola 2','053/675-111','www.licko-senjska.policija.hr',now(),now(),1);
#17
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('MEĐIMURSKA','Krunoslav Gosarić','Čakovec','Jakova Gotovca 7','040/373-111','www.medjimurska.policija.hr',now(),now(),1);
#18
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('POŽEŠKO-SLAVONSKA','Dario Dasović','Požega','Josipa Runjanina 1','034/254-111','www.pozesko-slavonska.policija.hr',now(),now(),1);
#19
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) 
values ('VARAŽDINSKA','Darko Jurčec','Varaždin','Ivana Milčetića 10','042/372-401','www.varazdinska.policija.hr',now(),now(),1);
#20
insert into policijskauprava(naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater)
values ('VIROVITIČKO-PODRAVSKA','Mirko Kostelac','Virovitica','Trg bana Josipa Jelačića 19','033/741-222','www.viroviticko-podravska.policija.hr',now(),now(),1);

-- GRAD (PU ZAGREBAČKA)

#1
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('ZAGREB','10000',1,now(),now(),1);
#2
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('SESVETE','10360',1,now(),now(),1);
#3
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('VELIKA GORICA','10410',1,now(),now(),1);
#4
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('ZAPREŠIĆ','10290',1,now(),now(),1);
#5
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('DUGO SELO','10370',1,now(),now(),1);
#6
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('JASTREBARSKO','10450',1,now(),now(),1);
#7
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('SAMOBOR','10430',1,now(),now(),1);
#8
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('SV.IVAN ZELINA','10380',1,now(),now(),1);
#9
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('VRBOVEC','10340',1,now(),now(),1);
#10
insert into grad(naziv,postanskibroj,policijskauprava,datumunosa,datumpromjene,operater)
values('IVANIĆ GRAD','10310',1,now(),now(),1);

-- GRAD (PU SPLITSKO-DALMATINSKA)

#11
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('SPLIT',2,now(),now(),1);
#12
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('BRAČ',2,now(),now(),1);
#13
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('HVAR',2,now(),now(),1);
#14
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('IMOTSKI',2,now(),now(),1);
#15
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('KAŠTELA',2,now(),now(),1);
#16
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('MAKARSKA',2,now(),now(),1);
#17
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('OMIŠ',2,now(),now(),1);
#18
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('SINJ',2,now(),now(),1);
#19
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('SOLIN',2,now(),now(),1);
#20
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('TROGIR',2,now(),now(),1);
#21
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('VIS',2,now(),now(),1);
#22
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('VRGORAC',2,now(),now(),1);

-- GRAD (PU PRIMORSKO-GORANSKA)

#23
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('RIJEKA',3,now(),now(),1);
#24
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('CRIKVENICA',3,now(),now(),1);
#25
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('ČABAR',3,now(),now(),1);
#26
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('DELNICE',3,now(),now(),1);
#27
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('KRK',3,now(),now(),1);
#28
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('OPATIJA',3,now(),now(),1);
#29
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('RAB',3,now(),now(),1);
#30
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('VRBOVSKO',3,now(),now(),1);

-- GRAD (PU OSJEČKO-BARANJSKA)

#31
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('OSIJEK',4,now(),now(),1);
#32
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('ČEPIN',4,now(),now(),1);
#33
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('BELI MANASTIR',4,now(),now(),1);
#34
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('DONJI MIHOLJAC',4,now(),now(),1);
#35
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('ĐAKOVO',4,now(),now(),1);
#36
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('VALPOVO',4,now(),now(),1);
#37
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('NAŠICE',4,now(),now(),1);
#38
insert into grad(naziv,policijskauprava,datumunosa,datumpromjene,operater)
values('DALJ',4,now(),now(),1);

-- POLICIJSKA POSTAJA (PU ZAGREBACKA)

#1
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater,slika)
values('I. PP ZAGREB','Dražen Kralj','T.J.J. Strossmayera 3','01/4563-520',1,now(),now(),1,'slike/prvaPostajaZagreb.jpg');
#2
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('II. PP ZAGREB','Igor Grivičić','Ilica 245','01/3775-924',1,now(),now(),1);
#3
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('III. PP ZAGREB','Jurica Miočić','Dubrava 137','01/2991-444',1,now(),now(),1);
#4
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('IV. PP ZAGREB','Stanko Tomić','Petrova 152','01/2302-122',1,now(),now(),1);
#5
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('V. PP ZAGREB','Davor Vuković','A. Bauera 1','01/4617-866',1,now(),now(),1);
#6
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('VI. PP ZAGREB','Roko Baotić','Trg Narodne zaštite 1','01/6141-455',1,now(),now(),1);
#7
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('VII. PP ZAGREB','Zoran Zdunić','Nehajska 7','01/3656-666',1,now(),now(),1);
#8
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('VIII. PP ZAGREB','Drago Barišić','Kruge 52','01/6305-711',1,now(),now(),1);

-- POLICIJSKA POSTAJA (PU SPLITSKO-DALMATINSKA)

#9
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater,slika)
values('I. PP SPLIT','Ilija Patrun','Put brodarice 6','021/504-510',11,now(),now(),1,'slike/prvaPostajaSplit.jpg');
#10
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('II. PP SPLIT','Siniša Mihanović','Pojišanska 2','021/309-356',11,now(),now(),1);

-- POLICIJSKA POSTAJA (PU PRIMORSKO-GORANSKA)

#11
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater,slika)
values('I. PP RIJEKA','Marijan Perica','Đure Šporera 4','051/335-234',23,now(),now(),1,'slike/prvaPostajaRijeka.jpg');
#12
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('II. PP RIJEKA','Ivica Filipović','Braće Monjac 24','051/439-110',23,now(),now(),1);
#13
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('III. PP RIJEKA','Milan Cindrić','Gundulićeva 7','051/439-610',23,now(),now(),1);

-- POLICIJSKA POSTAJA (PU OSJEČKO-BARANJSKA)

#14
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater,slika)
values('I. PP OSIJEK','Mario Horvat','Trg Lavoslava Ružičke 1','031/237-257',31,now(),now(),1,'slike/prvaPostajaOsijek.jpg');
#15
insert into policijskapostaja(naziv,nacelnik,adresa,telefon,grad,datumunosa,datumpromjene,operater)
values('II. PP OSIJEK','Tomislav Bilandžić','Prolaz kod Snježne Gospe 2','031/237-739',31,now(),now(),1);

-- AUTOMAT

#1
insert into automat(naziv,proizvodac,cijena,policijskapostaja,datumunosa,datumpromjene,operater)
values('MUPomat-ZG','Samsung',7000,1,now(),now(),1);
#2
insert into automat(naziv,proizvodac,cijena,policijskapostaja,datumunosa,datumpromjene,operater)
values('MUPomat-ST','Sony',10000,9,now(),now(),1); 
#3
insert into automat(naziv,proizvodac,cijena,policijskapostaja,datumunosa,datumpromjene,operater)
values('MUPomat-RI','Nokia',8500,11,now(),now(),1); 
#4
insert into automat(naziv,proizvodac,cijena,policijskapostaja,datumunosa,datumpromjene,operater)
values('MUPomat-OS','Alcatel',3000,14,now(),now(),1); 

-- OSOBA

#1
insert into osoba(oib,ime,prezime,email) values ('46530469259','Dario','Srdanović','dario.srdanovic@gmail.com');
insert into osoba(oib,ime,prezime,email) values ('12345678901','Ivan','Milanović','ivan.milanovic87@gmail.com');
insert into osoba(oib,ime,prezime,email) values ('12345678902','Tajana','Milanović','tajana.milanovic89@gmail.com');
insert into osoba(oib,ime,prezime,email) values ('12345678903','Danijel','Mustafić','danijel@vvk.hr');
insert into osoba(oib,ime,prezime,email) values ('12345678904','Krešimir','Sebauer','ksebauer@gmail.com');
insert into osoba(oib,ime,prezime,email) values ('12345678905','Iva','Smoljan','ksebauer@gmail.com');
insert into osoba(oib,ime,prezime,email) values ('12345678906','Tomislav','Jakopec','tjakopec@gmail.com');


-- KORISNIK

#1
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('46530469259',4,now(),'dsrdanovic',md5('dsrdanovic'),'korisnik',true);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678901',4,now(),'ivan',md5('ivan'),'korisnik',true);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678902',4,now(),'tajana',md5('tajana'),'korisnik',true);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678903',4,now(),'danijel',md5('danijel'),'korisnik',false);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678904',4,now(),'kreso',md5('kreso'),'korisnik',false);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678905',4,now(),'iva',md5('iva'),'korisnik',false);
insert into korisnik(oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values ('12345678906',4,now(),'tomislav',md5('tomislav'),'korisnik',false);

-- DOKUMENT

#1
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('1.','MBG',1,now(),now(),1);
#2
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('2.','PREBIVALIŠTE-BORAVIŠTE',1,now(),now(),1);
#3
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('3.','OSOBNA ISKAZNICA',1,now(),now(),1);
#4
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('4.','PUTOVNICA',1,now(),now(),1);
#5
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('5.','VOZAČKA DOZVOLA',1,now(),now(),1);
#6
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('6.','REGISTRACIJA VOZILA',1,now(),now(),1);
#7
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('7.','ORUŽJE',1,now(),now(),1);
#8
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('8.','DRŽAVLJANSTVO',1,now(),now(),1);
#9
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('9.','STRANCI',1,now(),now(),1);
#10
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('10.','ZAŠTITA OD POŽARA',1,now(),now(),1);
#11
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('11.','PROIZVODNJA I PROMET EKSPLOZIVNIH TVARI',1,now(),now(),1);
#12
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('12.','PRIVATNA ZAŠTITA',1,now(),now(),1);
#13
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('13.','HUMANITARNO RAZMINIRANJE',1,now(),now(),1);
#14
insert into dokument(rednibroj,naziv,automat,datumunosa,datumpromjene,operater)
values('14.','OBRASCI',1,now(),now(),1);








	
	
	
	
	
	
	
	
	


