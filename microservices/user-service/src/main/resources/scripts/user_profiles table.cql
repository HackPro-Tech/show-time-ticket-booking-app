create table user_profile(user_id uuid, firstname text,lastname text, 
email text, phone_no text,date_of_birth date,location text, 
gender text, email_notification boolean,genre set<text>,
created_at timestamp,modified_at timestamp,
primary key((user_id)));