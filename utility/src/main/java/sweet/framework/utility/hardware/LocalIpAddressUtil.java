package sweet.framework.utility.hardware;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 获取本地ip的工具
 *
 * @author pf-miles
 * @since 2014-11-25
 */
public final class LocalIpAddressUtil {

    /**
     * 获取本地ip地址，有可能会有多个地址, 若有多个网卡则会搜集多个网卡的ip地址
     */
    public static Set<InetAddress> resolveLocalAddress() {
        Set<InetAddress> addresses = new HashSet<>();
        Enumeration<NetworkInterface> ns = null;
        try {
            ns = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            // ignored...
        }
        while (ns != null && ns.hasMoreElements()) {
            NetworkInterface n = ns.nextElement();
            Enumeration<InetAddress> is = n.getInetAddresses();
            while (is.hasMoreElements()) {
                InetAddress i = is.nextElement();
                if (!i.isLoopbackAddress() && !i.isLinkLocalAddress() && !i.isMulticastAddress()
                        && !isSpecialIp(i.getHostAddress())) addresses.add(i);
            }
        }
        return addresses;
    }

    public static Set<String> resolveLocalIps() {
        Set<InetAddress> addresses = resolveLocalAddress();
        Set<String> ret = new HashSet<>();
        for (InetAddress addr : addresses)
            ret.add(addr.getHostAddress());
        return ret;
    }

    public static int parseIp(String address) {
        int result = 0;

        // iterate over each octet
        for (String part : address.split(Pattern.quote("."))) {
            // shift the previously parsed bits over by 1 byte
            result = result << 8;
            // set the low order bits to the current octet
            result |= Integer.parseInt(part);
        }
        return result;
    }

    public static int parseIp(InetAddress inetAddress) {
        int result = 0;
        for (byte b : inetAddress.getAddress()) {
            result = result << 8 | (b & 0xFF);
        }
        return result;
    }

    private static boolean isSpecialIp(String ip) {
        if (ip.contains(":")) return true;
        if (ip.startsWith("127.")) return true;
        if (ip.startsWith("169.254.")) return true;
        if (ip.equals("255.255.255.255")) return true;
        return false;
    }

    public static void main(String[] args) {
        Set<InetAddress> addresses = resolveLocalAddress();
        for (InetAddress address : addresses) {
            System.out.println(address.getHostAddress());
        }
    }
}