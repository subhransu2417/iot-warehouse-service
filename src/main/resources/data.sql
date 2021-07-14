DELETE FROM iot_device;
DELETE FROM sim;

INSERT INTO public.sim (sim_id,operator_code,country_name,sim_status,created_at,updated_at) VALUES
	 ('8bd9aa77-e7ca-4d39-9f6b-a3d5a89d196d','adfs','Italy','A','2021-07-12 22:33:23.000','2021-07-12 22:33:23.000');
INSERT INTO iot_device (device_id,device_status,device_temp,created_at,updated_at,sim_id) VALUES
	 ('d7668282-4a29-41f7-9180-cc7c2f33a447','READY','40','2021-07-12 22:33:23.000','2021-07-13 19:22:18.000','8bd9aa77-e7ca-4d39-9f6b-a3d5a89d196d');

INSERT INTO public.sim (sim_id,operator_code,country_name,sim_status,created_at,updated_at) VALUES
	 ('9761b862-69fe-4905-a0cf-a3a6abdc0519','adfs','Italy','A','2021-07-12 21:35:31.000','2021-07-12 21:35:31.000');
INSERT INTO iot_device (device_id,device_status,device_temp,created_at,updated_at,sim_id) VALUES
	 ('e29af59d-ee93-4581-9ad9-7f8d9b6c862f','READY','25','2021-07-12 21:35:31.000','2021-07-12 21:35:31.000','9761b862-69fe-4905-a0cf-a3a6abdc0519');
