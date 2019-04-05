import io.pkts.Pcap;
import io.pkts.packet.TCPPacket;
import io.pkts.protocol.Protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> payloads = new ArrayList<>();
        final Pcap pcap = Pcap.openStream("pcap-file.pcap");
        pcap.loop(packet -> {
                      if (packet.hasProtocol(Protocol.TCP)) {
                          final TCPPacket tcpPacket = (TCPPacket) packet.getPacket(Protocol.TCP);
                          if (tcpPacket.getPayload() != null) {
                              payloads.add(new String(tcpPacket.getPayload().getArray()));
                          }
                      }
                      return true;
                  }
        );
    }
}
