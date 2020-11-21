create table if not exist users{
    id int primary key not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    date_created timestamp not null;
}

create table if not exist accounts{
    id int primary key not null,
    account_number varchar(255) not null,
    account_type varchar(255) not null,
    balance double not null,
    date_created timestamp not null,
    user_id int,
    foreign key (user_id) references users(id);
}

create table if not exist transacs{
    id int primary key not null,
    transacs_type varchar(255) not null,
    transacs_amount double not null,
    is_success boolean not null,
    date_created timestamp not null,
    account_id int,
    foreign key (account_id) references accounts(id);
}

