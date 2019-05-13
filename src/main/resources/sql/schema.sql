create schema public
;

comment on schema public is 'standard public schema'
;

create table sweet_wrappers
(
	id bigserial not null
		constraint sweet_wrappers_pkey
			primary key,
	name varchar not null,
	type varchar not null,
	sugar_in_1000_mg bigint
)
;

create unique index sweet_wrappers_id_uindex
	on sweet_wrappers (id)
;

create unique index sweet_wrappers_name_uindex
	on sweet_wrappers (name)
;

create table sweet_fillings
(
	id bigserial not null
		constraint sweet_fillings_pkey
			primary key,
	name varchar not null,
	type varchar not null,
	sugar_in_1000_mg bigint
)
;

create unique index sweet_fillings_id_uindex
	on sweet_fillings (id)
;

create unique index sweet_fillings_name_uindex
	on sweet_fillings (name)
;

create table consumer_sweets
(
	id bigserial not null
		constraint consumer_sweets_pkey
			primary key,
	name varchar not null,
	type varchar not null,
	wrapper_weight bigint,
	filling_id bigint
		constraint consumer_sweets_sweet_fillings_id_fk
			references sweet_fillings,
	filling_weight bigint,
	wrapper_id bigint
		constraint consumer_sweets_sweet_wrappers_id_fk
			references sweet_wrappers
)
;

create unique index consumer_sweets_id_uindex
	on consumer_sweets (id)
;

create table seller_sweets
(
	id bigserial not null
		constraint seller_sweets_pkey
			primary key,
	name varchar not null,
	type varchar not null,
	wrapper_weight bigint,
	filling_id bigint
		constraint seller_sweets_sweet_fillings_id_fk
			references sweet_fillings,
	filling_weight bigint,
	wrapper_id bigint
		constraint seller_sweets_sweet_wrappers_id_fk
			references sweet_wrappers
)
;

create unique index seller_sweets_id_uindex
	on seller_sweets (id)
;

create unique index seller_sweets_name_uindex
	on seller_sweets (name)
;

create unique index consumer_sweets_name_uindex
	on seller_sweets (name)
;

