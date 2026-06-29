create table users (
   id uuid primary key,
   name varchar(255) not null,
   email varchar(255) not null,
   password varchar(255) not null,

   constraint uq_email unique (email)
);

create table zones (
   id uuid primary key,
   name varchar(255) not null,
   floor smallint not null,

   constraint uq_zone_name unique (name)
);

create table workspaces (
    id uuid primary key,
    name varchar(255),
    zone_id uuid not null,
    type varchar(32) not null,
    row_label varchar(1),
    desk_number integer,
    capacity integer not null,

    constraint fk_workspaces_zone
        foreign key (zone_id)
            references zones(id)
            on delete restrict,

    constraint uq_workspace_zone_row_label_desk_number
        unique (zone_id, row_label, desk_number),

    constraint chk_workspace_type
        check (type in ('DESK', 'MEETING_ROOM', 'GAME_ROOM')),

    constraint chk_workspace_capacity_positive
        check (capacity >= 1),

    constraint chk_workspace_row_label
        check (row_label is null or row_label ~ '^[A-Z]$'),

    constraint chk_workspace_desk_number
        check (desk_number is null or desk_number between 1 and 255),

    constraint chk_workspace_desk_fields
        check ((type = 'DESK' and row_label is not null and desk_number is not null) or (type <> 'DESK'))
);

create index idx_workspace_zone
    on workspaces(zone_id);

create table bookings (
    id uuid primary key,
    starts_at timestamp not null,
    ends_at timestamp not null,
    booker_id uuid not null,
    workspace_id uuid not null,

    constraint fk_bookings_booker
      foreign key (booker_id)
          references users(id)
          on delete restrict,

    constraint fk_bookings_workspace
      foreign key (workspace_id)
          references workspaces(id)
          on delete restrict,

    constraint chk_booking_time
      check (ends_at > starts_at)
);

create index idx_bookings_booker_id
    on bookings(booker_id);

create index idx_bookings_workspace_id
    on bookings(workspace_id);

create index idx_bookings_workspace_starts_at
    on bookings(workspace_id, starts_at);