create extension if not exists "uuid-ossp";

create table portfolios (
  id uuid not null primary key default uuid_generate_v4(),
  name text not null
);

create table holdings (
  portfolio_id uuid not null,
  symbol text primary key not null,
  shares decimal not null,
  price decimal not null,
  constraint fk_portfolio foreign key(portfolio_id) references portfolios(id)
);

create table statements (
  id uuid not null primary key default uuid_generate_v4(),
  portfolio_id uuid not null,
  balance decimal not null,
  income decimal not null,
  statement_date date not null,
  constraint fk_portfolio foreign key(portfolio_id) references portfolios(id)
);

drop database if exists test_db;
create database test_db;
