drop table if exists clients;
create table clients (
    id integer not null primary key auto_increment,
    name varchar(125) not null
);

drop table if exists contacts;
create table contacts (
    contact_id integer not null primary key,
    client_id integer not null,
    contact_type varchar(100) not null,
    contact_value varchar(255) not null,
    foreign key (client_id) references clients (id)
)
