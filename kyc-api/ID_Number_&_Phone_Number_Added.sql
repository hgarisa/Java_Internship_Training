ALTER TABLE public.customers
  ADD COLUMN id_number varchar(50),
  ADD COLUMN phone_number varchar(30);

-- If you want id_number mandatory (recommended), do it in 2 steps:
-- 1) populate existing rows with something temporary (or your real values)
UPDATE public.customers
SET id_number = 'TEMP-' || id
WHERE id_number IS NULL;

-- 2) enforce constraints
ALTER TABLE public.customers
  ALTER COLUMN id_number SET NOT NULL;

-- unique business id
CREATE UNIQUE INDEX ux_customers_id_number ON public.customers(id_number);

