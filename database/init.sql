create schema demo
create table demo.message
(
    id uniqueidentifier
        constraint message_pk
            primary key nonclustered,
    value nvarchar(max)
)
go
