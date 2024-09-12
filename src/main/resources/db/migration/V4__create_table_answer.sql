create table answer(
    id bigint not null auto_increment,
    message varchar(500) not null,
    creation_date datetime not null,
    status varchar(15) not null,
    author_id bigint not null,
    topic_id bigint not null,
    is_solution boolean not null,
    primary key(id),
    foreign key(author_id) references users(id),
    foreign key(topic_id) references topic(id)
);

