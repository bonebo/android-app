package net.ivpn.client.rest.data.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.ivpn.client.vpn.Protocol;

public class Server {

    public static Comparator<Server> comparator = (server1, server2) -> {
        int countryCode = server1.countryCode.compareTo(server2.countryCode);
        if (countryCode != 0){
            return countryCode;
        }
        return server1.city.compareTo(server2.city);
    };

    @SerializedName("gateway")
    @Expose
    private String gateway;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("ip_addresses")
    @Expose
    private List<String> ipAddresses = null;
    @SerializedName("hosts")
    @Expose
    private List<Host> hosts = null;
    @SerializedName("protocol")
    @Expose
    private Protocol type;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public String getDescription() {
        return city + ", " + countryCode;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public Protocol getType() {
        return type;
    }

    public void setType(Protocol type) {
        this.type = type;
    }

    public boolean canBeUsedAsMultiHopWith(Server server) {
        if (server == null) return true;
        return !this.countryCode.equalsIgnoreCase(server.countryCode);
    }

    @Override
    public String toString() {
        return "Server{" +
                "gateway='" + gateway + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", ipAddresses=" + ipAddresses +
                ", hosts=" + hosts +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Server)) {
            return false;
        }
        Server other = (Server) obj;
        if (Objects.equals(this.gateway, other.gateway)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (gateway != null) {
            return gateway.hashCode();
        }
        return super.hashCode();
    }
}
