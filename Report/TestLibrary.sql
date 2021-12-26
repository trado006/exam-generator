create table Subject(
	subid int not null,
	name text,
	constraint mainsub primary key subid
);
create table Chapter(
	cid int not null,
	name text,
	subid int not null,
	constraint mainchap primary key cid,
	constraint chaptosubject FOREIGN KEY subid
						REFERENCES Subject(subid)
);
create table Question(
	qid int not null,
	content text,
	choose text,
	answer1 text,
	answer2 text,
	answer3 text,
	cid int not null,
	constraint mainques primary key qid,
	constraint questochap foreign key cid
					references Chapter(cid),
);

create table Test(
	testid int not null,
	testtype text,
	subid int not null,
	constraint maintest primary key (testid,subid),
	constraint testtosub foreign key subid
						references Subject(subid)
);
create  table Struct(
	subid int not null,
	testid int not null,
	cid int not null,
	stacount int not null,
	constraint mainstr primary key (subid,testid,cid),
	constraint strtosub foreign key subid
						references Subject(subid),
	constraint strtotest foreign key testid
						references Test(testid),
	constraint strtochap foreign key cid
						references Chapter(cid)
);
create table Testquestion(
	testid int not null,
	qid int not null,
	subid int not null,
	constraint maintestqes primary key(subid, testid, subid),
	constraint testqestotest foreign key testid
							references Test(testid),
	constraint testqestosub foreign key subid
							references Subject(subid),
	constraint testqestoqes foreign key qid
						references Question(qid)
);