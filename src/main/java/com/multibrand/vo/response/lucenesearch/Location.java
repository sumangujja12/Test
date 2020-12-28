package com.multibrand.vo.response.lucenesearch;

import java.util.List;

/** This class will be used in GSON mapping the Search Response 
 * 
 * @author RKiran
 */
public class Location {

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Capabilities> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capabilities> capabilities) {
		this.capabilities = capabilities;
	}

	private Address address;

	private List<Capabilities> capabilities;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
}