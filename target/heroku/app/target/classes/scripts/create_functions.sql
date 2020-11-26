CREATE OR REPLACE FUNCTION add_new_account()
  RETURNS TRIGGER
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
		 INSERT INTO accounts(id,account_number,account_type, balance, date_created, user_id)
		 VALUES(nextval('mysequence'), uuid_generate_v4(), 'SAVINGS', 0, now(), NEW.id);

	RETURN NEW;
END;
$$