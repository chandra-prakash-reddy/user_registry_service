drop schema if exists registry cascade;
create schema registry;

drop table if exists registry.portal_users cascade;
create table registry.portal_users (
userid varchar not null,
username varchar,
password varchar,
email varchar,
crt_ts timestamp
);
ALTER TABLE registry.portal_users ADD PRIMARY KEY (userid);

drop table if exists registry.employee_details cascade;
create table  registry.employee_details(
employee_id varchar not null,
employee_first_name varchar not null,
employee_last_name varchar ,
employee_profile_url varchar,
employee_email varchar,
employee_phone varchar,
crt_ts timestamp,
crt_by varchar,
mod_ts timestamp,
mod_by varchar
);

ALTER TABLE registry.employee_details  ADD  PRIMARY KEY (employee_id);
ALTER TABLE registry.employee_details ADD FOREIGN KEY (crt_by)  REFERENCES registry.portal_users (userid);
ALTER TABLE registry.employee_details ADD FOREIGN KEY (mod_by)  REFERENCES registry.portal_users (userid);