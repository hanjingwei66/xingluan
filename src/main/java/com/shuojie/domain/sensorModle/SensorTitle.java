package com.shuojie.domain.sensorModle;

import lombok.Data;

@Data
public class SensorTitle {
    byte version;//版本
    byte comand;//2 命令字
    int jizhongqid;//6 集中器 ID
    int jiedianid;//节点 ID
    short duanid;//短 ID
    byte tongdao;//通道0x01-0x04
    byte snr;//SNR
    byte rssi0;//RSSI[0]
    byte rssi1;//12 RSSI[1]0x01:RSSI 为正数，0x00:RSSI 为负数
    byte nc;//13 NC
    byte nc1;//14 NC
    int time;//15 时间戳 0x01：掉线，0x00：在线
    byte zdzxqk;//16 终端在线情况
    short num;//17 终端入网总数
    short dataLength;// 18 数据长度
    String data;//19 有效数据
}
