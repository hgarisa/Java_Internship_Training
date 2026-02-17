ALTER TABLE public.customer_read_audit
  ADD COLUMN customer_id_number varchar(50);

ALTER TABLE public.customer_change_audit
  ADD COLUMN customer_id_number varchar(50);