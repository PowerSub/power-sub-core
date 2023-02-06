--liquibase formatted sql
--changeset neznaika_38:1
CREATE TABLE IF NOT EXISTS public.channels
(
    channel_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    title character varying(256) COLLATE pg_catalog."default" NOT NULL,
    description character varying(1024) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT channels_pkey PRIMARY KEY (channel_id)
)