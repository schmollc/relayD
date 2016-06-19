package com.relayd.ejb;

import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 17.06.2016
 * status initial
 */
public class RelayEventGatewayFactory {

	public static RelayEventGatewayMemory get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new RelayEventGatewayMemory();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}