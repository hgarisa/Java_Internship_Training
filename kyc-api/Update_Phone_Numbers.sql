UPDATE public.customers
SET phone_number =
  CASE
    WHEN phone_number ~ '^\+27' THEN phone_number
    WHEN phone_number ~ '^0[0-9]{9}$' THEN '+27' || substring(phone_number from 2)
    WHEN phone_number ~ '^27[0-9]{9}$' THEN '+27' || substring(phone_number from 3)
    ELSE phone_number
  END
WHERE phone_number IS NOT NULL;
