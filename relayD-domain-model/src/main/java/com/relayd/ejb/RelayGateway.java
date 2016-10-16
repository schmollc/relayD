package com.relayd.ejb;

import java.util.List;

import com.relayd.Relay;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
public interface RelayGateway {
	List<Relay> getAll();

	void set(Relay aRelay);
}