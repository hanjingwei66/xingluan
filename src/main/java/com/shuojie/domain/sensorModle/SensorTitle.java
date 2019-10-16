package com.shuojie.domain.sensorModle;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SensorTitle {
    private Long sensorId;//传感器id
    private String sensorName;//传感器名字
    private Integer sensorType;//传感器类型 0 测角 1测距 2 激光 3十轴
    private Integer power;//电量 0-100;
    private Integer status;//状态0，正常 1，非正常
    private Integer signal ;//信号 0 无信号，1 信号弱， 2 信号差， 3 信号中4 信号好
    @TableId
    private Long id;
    private byte version;//版本
    private  byte comand;//2 命令字
    private int jizhongqid;//6 集中器 ID
    private int jiedianid;//节点 ID
    private short duanid;//短 ID
    private byte tongdao;//通道0x01-0x04
    private byte snr;//SNR信号
    private byte rssi0;//RSSI[0]
    private byte rssi1;//12 RSSI[1]0x01:RSSI 为正数，0x00:RSSI 为负数
    private  byte nc;//13 NC
    private  byte nc1;//14 NC
    private  int time;//15 时间戳 0x01：掉线，0x00：在线
    private byte zdzxqk;//16 终端在线情况
    private  short num;//17 终端入网总数
    private short sensorDataLength;// 18 数据长度
    private String sensorData;//19 有效数据
}
