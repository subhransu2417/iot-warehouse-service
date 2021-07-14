--DROP TABLE IF EXISTS sim;

CREATE TABLE IF NOT EXISTS public.sim (
	sim_id varchar NOT NULL,
	operator_code varchar NOT NULL,
	country_name varchar NOT NULL,
	sim_status varchar NOT NULL,
	created_at timestamp(0) NULL,
	updated_at timestamp(0) NULL,
	CONSTRAINT sim_pk PRIMARY KEY (sim_id)
);

--DROP TABLE IF EXISTS iot_device;

CREATE TABLE IF NOT EXISTS public.iot_device (
	device_id varchar NOT NULL,
	device_status varchar NOT NULL,
	device_temp varchar NOT NULL,
	created_at timestamp(0) NOT NULL,
	updated_at timestamp(0) NULL,
	sim_id varchar NOT NULL,
	CONSTRAINT iot_device_pk PRIMARY KEY (device_id),
	CONSTRAINT iot_device_fk FOREIGN KEY (sim_id) REFERENCES public.sim(sim_id)
);