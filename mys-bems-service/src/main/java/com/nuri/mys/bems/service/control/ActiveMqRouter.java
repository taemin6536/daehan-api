package com.nuri.mys.bems.service.control;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqRouter extends RouteBuilder {
	private static final Logger log = LoggerFactory.getLogger(ActiveMqRouter.class);
	
	@Autowired
	private CamelContext camelContext;
	
	@Override
	public void configure() throws Exception {
		
		camelContext.setStreamCaching(false);

		from("jms:topic:pms/ems/pcs/monitor")
		.id("RealtimePcs")
		.to("bean:pushHandler?method=sendDeviceRealtime")
		.end();

		from("jms:topic:pms/ems/bms/monitor")
		.id("RealtimeBms")
		.to("bean:pushHandler?method=sendDeviceRealtime")
		.end();

		from("jms:topic:pms/ems/pvi/monitor")
		.id("RealtimePvi")
		.to("bean:pushHandler?method=sendDeviceRealtime")
		.end();

		from("jms:topic:pms/ems/pvs/monitor")
		.id("RealtimePvs")
		.to("bean:pushHandler?method=sendDeviceRealtime")
		.end();

		from("jms:topic:pms/ems/ths/monitor")
		.id("RealtimeThs")
		.to("bean:pushHandler?method=sendDeviceRealtime")
		.end();

		from("jms:topic:pms/ems/all/events")
		.id("RealtimeEvent")
		.to("bean:pushHandler?method=sendEventRealtime")
		.end();
	}
}
