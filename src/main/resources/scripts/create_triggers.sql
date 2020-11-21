CREATE TRIGGER add_new_account
  AFTER INSERT
  ON users
  FOR EACH ROW
  EXECUTE PROCEDURE add_new_account();