--liquibase formatted sql
--changeset neznaika_38:1
CREATE TABLE IF NOT EXISTS public.subscribes
(
    account_id bigint NOT NULL,
    channel_id bigint NOT NULL,
    CONSTRAINT subscribes_pkey PRIMARY KEY (channel_id, account_id),
    CONSTRAINT subscr_to_account FOREIGN KEY (account_id)
        REFERENCES public.accounts (account_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT subscr_to_channel FOREIGN KEY (channel_id)
        REFERENCES public.channels (channel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)