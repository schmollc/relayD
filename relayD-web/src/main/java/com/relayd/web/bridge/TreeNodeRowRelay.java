package com.relayd.web.bridge;

import com.relayd.Relay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
public class TreeNodeRowRelay extends TreeNodeRow {
	private static final long serialVersionUID = -222411690063227304L;

	private Relay relay; // TODO - REL-280 - Mit Erik sprechen. Eine Relay hat auch ein NOP?

	TreeNodeRowRelay(Relay aRelay) {
		relay = aRelay;
	}

	@Override
	public Relay getRelay() {
		return relay;
	}

	@Override
	public boolean isRelay() {
		return true;
	}

	@Override
	public String getRelayname() {
		return relay.toString();
	}

	@Override
	public String getShirtsize() {
		return "";
	}

	@Override
	public String getDuration() {
		return relay.getDurationFormatted();
	}

	@Override
	public String getStatus() {
		if (relay.isEmpty()) {
			return "ui-icon ui-icon-help";
		} else if (relay.isFilled()) {
			return "ui-icon ui-icon-check";
		}
		return "ui-icon ui-icon-notice";
	}
}