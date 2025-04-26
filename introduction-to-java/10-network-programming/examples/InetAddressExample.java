/**
 * InetAddressExample.java
 * This program demonstrates working with IP addresses using the InetAddress class.
 */
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class InetAddressExample {
    public static void main(String[] args) {
        System.out.println("--- InetAddress Examples ---");
        
        // Example 1: Getting the local host address
        System.out.println("\nExample 1: Getting the local host address");
        getLocalHost();
        
        // Example 2: Getting address by name
        System.out.println("\nExample 2: Getting address by name");
        getAddressByName("www.google.com");
        getAddressByName("www.example.com");
        
        // Example 3: Getting all addresses for a host
        System.out.println("\nExample 3: Getting all addresses for a host");
        getAllAddresses("www.google.com");
        
        // Example 4: Checking if an address is reachable
        System.out.println("\nExample 4: Checking if an address is reachable");
        checkReachable("www.google.com");
        checkReachable("www.example.com");
        
        // Example 5: Working with IPv4 and IPv6 addresses
        System.out.println("\nExample 5: Working with IPv4 and IPv6 addresses");
        workWithIPv4AndIPv6();
        
        // Example 6: Getting network interfaces
        System.out.println("\nExample 6: Getting network interfaces");
        getNetworkInterfaces();
    }
    
    /**
     * Demonstrates getting the local host address.
     */
    private static void getLocalHost() {
        try {
            // Get the local host address
            InetAddress localHost = InetAddress.getLocalHost();
            
            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Local Host Address: " + localHost.getHostAddress());
            System.out.println("Local Host toString: " + localHost.toString());
            System.out.println("Local Host Canonical Host Name: " + localHost.getCanonicalHostName());
        } catch (UnknownHostException e) {
            System.out.println("Error getting local host: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates getting an address by name.
     */
    private static void getAddressByName(String host) {
        try {
            // Get address by name
            InetAddress address = InetAddress.getByName(host);
            
            System.out.println(host + " - Host Name: " + address.getHostName());
            System.out.println(host + " - Host Address: " + address.getHostAddress());
            System.out.println(host + " - toString: " + address.toString());
            System.out.println(host + " - Canonical Host Name: " + address.getCanonicalHostName());
            
            // Check if it's a loopback address
            System.out.println(host + " - Is Loopback Address: " + address.isLoopbackAddress());
            
            // Check if it's a multicast address
            System.out.println(host + " - Is Multicast Address: " + address.isMulticastAddress());
            
            // Get the address bytes
            byte[] addressBytes = address.getAddress();
            System.out.print(host + " - Address Bytes: ");
            for (byte b : addressBytes) {
                System.out.print((b & 0xff) + ".");
            }
            System.out.println();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + host + " - " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates getting all addresses for a host.
     */
    private static void getAllAddresses(String host) {
        try {
            // Get all addresses for a host
            InetAddress[] addresses = InetAddress.getAllByName(host);
            
            System.out.println("Found " + addresses.length + " address(es) for " + host + ":");
            
            for (int i = 0; i < addresses.length; i++) {
                System.out.println("Address " + (i + 1) + ":");
                System.out.println("  Host Name: " + addresses[i].getHostName());
                System.out.println("  Host Address: " + addresses[i].getHostAddress());
                
                // Check if it's IPv4 or IPv6
                if (addresses[i] instanceof Inet4Address) {
                    System.out.println("  Address Type: IPv4");
                } else if (addresses[i] instanceof Inet6Address) {
                    System.out.println("  Address Type: IPv6");
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + host + " - " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates checking if an address is reachable.
     */
    private static void checkReachable(String host) {
        try {
            InetAddress address = InetAddress.getByName(host);
            
            // Try to reach the address with a timeout of 5 seconds
            System.out.println("Trying to reach " + host + "...");
            boolean reachable = address.isReachable(5000);
            
            if (reachable) {
                System.out.println(host + " is reachable");
            } else {
                System.out.println(host + " is not reachable");
            }
        } catch (Exception e) {
            System.out.println("Error checking if " + host + " is reachable: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates working with IPv4 and IPv6 addresses.
     */
    private static void workWithIPv4AndIPv6() {
        try {
            // IPv4 address
            InetAddress ipv4Address = InetAddress.getByName("192.168.1.1");
            System.out.println("IPv4 Address: " + ipv4Address.getHostAddress());
            System.out.println("Is IPv4: " + (ipv4Address instanceof Inet4Address));
            
            // IPv6 address (example)
            try {
                InetAddress ipv6Address = InetAddress.getByName("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
                System.out.println("IPv6 Address: " + ipv6Address.getHostAddress());
                System.out.println("Is IPv6: " + (ipv6Address instanceof Inet6Address));
            } catch (UnknownHostException e) {
                System.out.println("IPv6 example address not available: " + e.getMessage());
            }
            
            // Try to get an IPv6 loopback address
            InetAddress ipv6Loopback = InetAddress.getByName("::1");
            System.out.println("IPv6 Loopback: " + ipv6Loopback.getHostAddress());
            System.out.println("Is IPv6: " + (ipv6Loopback instanceof Inet6Address));
            System.out.println("Is Loopback: " + ipv6Loopback.isLoopbackAddress());
        } catch (UnknownHostException e) {
            System.out.println("Error working with IP addresses: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates getting network interfaces.
     */
    private static void getNetworkInterfaces() {
        try {
            // Get all network interfaces
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                
                // Skip inactive interfaces
                if (!networkInterface.isUp()) {
                    continue;
                }
                
                System.out.println("Interface: " + networkInterface.getName() + " (" + networkInterface.getDisplayName() + ")");
                System.out.println("  MAC Address: " + getMacAddress(networkInterface));
                System.out.println("  MTU: " + networkInterface.getMTU());
                System.out.println("  Is Loopback: " + networkInterface.isLoopback());
                System.out.println("  Is Point-to-Point: " + networkInterface.isPointToPoint());
                System.out.println("  Is Virtual: " + networkInterface.isVirtual());
                
                // Get IP addresses associated with this interface
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    
                    String type = (address instanceof Inet4Address) ? "IPv4" : "IPv6";
                    System.out.println("  " + type + " Address: " + address.getHostAddress());
                }
                
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error getting network interfaces: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to get the MAC address of a network interface.
     */
    private static String getMacAddress(NetworkInterface networkInterface) {
        try {
            byte[] mac = networkInterface.getHardwareAddress();
            
            if (mac == null) {
                return "Not Available";
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
            }
            
            return sb.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
