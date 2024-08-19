
create table board(
	id int primary key auto_increment,
    title varchar(20) not null,
    content varchar(20) not null,
    author varchar(100) not null,
    created_at timestamp default current_timestamp
);
