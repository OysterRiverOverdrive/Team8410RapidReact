// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

import edu.wpi.first.wpilibj.*;
import java.nio.ByteBuffer;
import java.util.concurrent.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Driver for the TCS34725 RGB color sensor. Adapted for roboRio/WPILib
 * based on code from <a href=
 * "https://github.com/OlivierLD/raspberry-coffee/blob/master/I2C.SPI/src/i2c/sensor/TCS34725.java">raspberry-coffee</a>.
 * This code was tested using the
 * <a href="https://www.adafruit.com/product/1334">Adafruit color sensor.</a>
 * Connect sensor to roboRio I2C port and instantiate class. Component will
 * automatically feed values to the smartdashboard
 * keys "RGBColorSensor/[R,G,B,C]".
 * 
 * @author Chuck Benedict, Mentor, Team 997
 */
public class Color_TCS34725_I2C {
    protected I2C i2c;

    public boolean statusIsGood = false;

    public final static int TCS34725_COMMAND_BIT = 0x80;
    public final static int TCS34725_COMMAND_AUTO_INCREMENT = 0x20;
    public final static int TCS34725_ADDRESS = 0x29;
    public final static int TCS34725_ENABLE = 0x00;
    public final static int TCS34725_ENABLE_AIEN = 0x10; // RGBC Interrupt Enable
    public final static int TCS34725_ENABLE_WEN = 0x08; // Wait enable - Writing 1 activates the wait timer
    public final static int TCS34725_ENABLE_AEN = 0x02; // RGBC Enable - Writing 1 actives the ADC, 0 disables it
    public final static int TCS34725_ENABLE_PON = 0x01; // Power on - Writing 1 activates the internal oscillator, 0
                                                        // disables it
    public final static int TCS34725_ATIME = 0x01; // Integration time
    public final static int TCS34725_WTIME = 0x03; // Wait time (if TCS34725_ENABLE_WEN is asserted)
    public final static int TCS34725_WTIME_2_4MS = 0xFF; // WLONG0 = 2.4ms WLONG1 = 0.029s
    public final static int TCS34725_WTIME_204MS = 0xAB; // WLONG0 = 204ms WLONG1 = 2.45s
    public final static int TCS34725_WTIME_614MS = 0x00; // WLONG0 = 614ms WLONG1 = 7.4s
    public final static int TCS34725_AILTL = 0x04; // Clear channel lower interrupt threshold
    public final static int TCS34725_AILTH = 0x05;
    public final static int TCS34725_AIHTL = 0x06; // Clear channel upper interrupt threshold
    public final static int TCS34725_AIHTH = 0x07;
    public final static int TCS34725_PERS = 0x0C; // Persistence register - basic SW filtering mechanism for interrupts
    public final static int TCS34725_PERS_NONE = 0b0000; // Every RGBC cycle generates an interrupt
    public final static int TCS34725_PERS_1_CYCLE = 0b0001; // 1 clean channel value outside threshold range generates
                                                            // an interrupt
    public final static int TCS34725_PERS_2_CYCLE = 0b0010; // 2 clean channel values outside threshold range generates
                                                            // an interrupt
    public final static int TCS34725_PERS_3_CYCLE = 0b0011; // 3 clean channel values outside threshold range generates
                                                            // an interrupt
    public final static int TCS34725_PERS_5_CYCLE = 0b0100; // 5 clean channel values outside threshold range generates
                                                            // an interrupt
    public final static int TCS34725_PERS_10_CYCLE = 0b0101; // 10 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_15_CYCLE = 0b0110; // 15 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_20_CYCLE = 0b0111; // 20 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_25_CYCLE = 0b1000; // 25 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_30_CYCLE = 0b1001; // 30 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_35_CYCLE = 0b1010; // 35 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_40_CYCLE = 0b1011; // 40 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_45_CYCLE = 0b1100; // 45 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_50_CYCLE = 0b1101; // 50 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_55_CYCLE = 0b1110; // 55 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_PERS_60_CYCLE = 0b1111; // 60 clean channel values outside threshold range
                                                             // generates an interrupt
    public final static int TCS34725_CONFIG = 0x0D;
    public final static int TCS34725_CONFIG_WLONG = 0x02; // Choose between short and long (12x) wait times via
                                                          // TCS34725_WTIME
    public final static int TCS34725_CONTROL = 0x0F; // Set the gain level for the sensor
    public final static int TCS34725_ID = 0x12; // 0x44 = TCS34721/TCS34725, 0x4D = TCS34723/TCS34727
    public final static int TCS34725_STATUS = 0x13;
    public final static int TCS34725_STATUS_AINT = 0x10; // RGBC Clean channel interrupt
    public final static int TCS34725_STATUS_AVALID = 0x01; // Indicates that the RGBC channels have completed an
                                                           // integration cycle

    public final static int TCS34725_CDATAL = 0x14; // Clear channel data
    public final static int TCS34725_CDATAH = 0x15;
    public final static int TCS34725_RDATAL = 0x16; // Red channel data
    public final static int TCS34725_RDATAH = 0x17;
    public final static int TCS34725_GDATAL = 0x18; // Green channel data
    public final static int TCS34725_GDATAH = 0x19;
    public final static int TCS34725_BDATAL = 0x1A; // Blue channel data
    public final static int TCS34725_BDATAH = 0x1B;

    public final static int TCS34725_INTEGRATIONTIME_2_4MS = 0xFF; // 2.4ms - 1 cycle - Max Count: 1024
    public final static int TCS34725_INTEGRATIONTIME_24MS = 0xF6; // 24ms - 10 cycles - Max Count: 10240
    public final static int TCS34725_INTEGRATIONTIME_50MS = 0xEB; // 50ms - 20 cycles - Max Count: 20480
    public final static int TCS34725_INTEGRATIONTIME_101MS = 0xD5; // 101ms - 42 cycles - Max Count: 43008
    public final static int TCS34725_INTEGRATIONTIME_154MS = 0xC0; // 154ms - 64 cycles - Max Count: 65535
    public final static int TCS34725_INTEGRATIONTIME_700MS = 0x00; // 700ms - 256 cycles - Max Count: 65535

    public final static int TCS34725_GAIN_1X = 0x00; // 1x gain
    public final static int TCS34725_GAIN_4X = 0x01; // 4x gain
    public final static int TCS34725_GAIN_16X = 0x02; // 16x gain
    public final static int TCS34725_GAIN_60X = 0x03; // 60x gain

    public final static Map<Integer, Long> INTEGRATION_TIME_DELAY = new HashMap<>();

    static { // Microseconds
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_2_4MS, 2400L); // 2.4ms - 1 cycle - Max Count: 1024
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_24MS, 24_000L); // 24ms - 10 cycles - Max Count: 10240
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_50MS, 50_000L); // 50ms - 20 cycles - Max Count: 20480
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_101MS, 101_000L); // 101ms - 42 cycles - Max Count: 43008
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_154MS, 154_000L); // 154ms - 64 cycles - Max Count: 65535
        INTEGRATION_TIME_DELAY.put(TCS34725_INTEGRATIONTIME_700MS, 700_000L); // 700ms - 256 cycles - Max Count: 65535
    }

    private boolean verbose = true;
    final ExecutorService executorService = Executors.newFixedThreadPool(1);
    Future<?> integrationFuture = null; // This future will ensure that the requisite integration time has elapsed

    public final static int INTEGRATION_TIME_DEFAULT = TCS34725_INTEGRATIONTIME_24MS;
    public final static int GAIN_DEFAULT = TCS34725_GAIN_4X;
    private int integrationTime;
    private int gain;

    /**
     * Constructs the TCS34725 RGB color sensor over onboard I2C port.
     * 
     * @param verbose If true, spew helpful messages to console. If omitted, assume
     *                false.
     */
    public Color_TCS34725_I2C() {
        i2c = new I2C(I2C.Port.kOnboard, TCS34725_ADDRESS);
    }

    /**
     * Check to make sure the I2C device we are expecting to be on the bus actually
     * is.
     * Then, enable it.
     * 
     * @throws Exception
     */
    public void initialize(int integrationTime, int gain) throws TransferAbortedException, InterruptedException {
        if (gain > TCS34725_GAIN_60X | gain < 0) {
            throw new IllegalArgumentException("Gain not valid.");
        }
        int result = this.readU8(TCS34725_ID);
        if (result != 0x44) {
            throw new RuntimeException("Device is not a TCS34721/TCS34725");
        }
        // Set startup integration time and gain
        // setIntegrationTime(integrationTime);
        setIntegrationTime(TCS34725_INTEGRATIONTIME_700MS);
        setGain(gain);
        // Power on
        enable();
        statusIsGood = true;
        if (verbose) {
            System.out.println("TCS34725 initialized");
        }
    }

    private void setIntegrationTimeAwaiter() {
        integrationFuture = executorService.submit(
                () -> {
                    try {
                        Thread.sleep((long) (INTEGRATION_TIME_DELAY.get(this.integrationTime) / 1_000L));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Enable color sensor sensing.
     * 
     * @throws InterruptedException
     * @throws TransferAbortedException
     */
    public void enable() throws TransferAbortedException, InterruptedException {
        // initialize(integrationTime,gain);
        this.write8(TCS34725_ENABLE, TCS34725_ENABLE_PON);
        Thread.sleep(10L); // Per datasheet, at least 2.4ms must elapse before AEN can be asserted
        this.write8(TCS34725_ENABLE, (TCS34725_ENABLE_PON | TCS34725_ENABLE_AEN));
        setIntegrationTimeAwaiter();
        if (verbose) {
            System.out.println("TCS34725 enabled");
        }
    }

    public boolean isEnabled() throws TransferAbortedException {
        int reg = 0;
        reg = this.readU8(TCS34725_ENABLE);
        return ((reg & TCS34725_ENABLE_PON) != 0 && (reg & TCS34725_ENABLE_AEN) != 0);
    }

    public void disable() throws TransferAbortedException, InterruptedException {
        // Datasheet does not say it explicitly, but you must wait the 2.4ms between
        // turning off AEN and PON in order to get the device into the sleep state.
        int reg = 0;
        reg = this.readU8(TCS34725_ENABLE);
        this.write8(TCS34725_ENABLE, (reg & ~(TCS34725_ENABLE_AEN)));
        Thread.sleep(10L);
        this.write8(TCS34725_ENABLE, (reg & ~(TCS34725_ENABLE_PON | TCS34725_ENABLE_AEN)));
    }

    public void setIntegrationTime(int integrationTime) throws TransferAbortedException {
        this.integrationTime = integrationTime;
        this.write8(TCS34725_ATIME, integrationTime);
    }

    public int getIntegrationTime() throws TransferAbortedException {
        return this.readU8(TCS34725_ATIME);
    }

    public void setGain(int gain) throws TransferAbortedException {
        this.gain = gain;
        this.write8(TCS34725_CONTROL, gain);
    }

    public int getGain() throws TransferAbortedException {
        return this.readU8(TCS34725_CONTROL);
    }

    public TCS34725_Values getRawData() throws TransferAbortedException {
        int r = 0;
        int b = 0;
        int g = 0;
        int c = 0;
        try {
            // Wait on the integration time future
            integrationFuture.get();
            r = this.readU16(TCS34725_RDATAL);
            b = this.readU16(TCS34725_BDATAL);
            g = this.readU16(TCS34725_GDATAL);
            c = this.readU16(TCS34725_CDATAL);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Integration time awaiter error: " + e);
        } finally {
            // Reset the integration time future so that polling this method causes enough
            // time
            // to elapse between samples.
            setIntegrationTimeAwaiter();
        }
        return new TCS34725_Values(r, b, g, c);
    }

    public void setInterrupt(boolean intrpt) throws Exception {
        int r = this.readU8(TCS34725_ENABLE);
        if (intrpt) {
            r |= TCS34725_ENABLE_AIEN;
        } else {
            r &= ~TCS34725_ENABLE_AIEN;
        }
        this.write8(TCS34725_ENABLE, r);
    }

    /**
     * Write a byte to a register
     * 
     * @param register The register to write to
     * @param value    The value to write
     * @throws TransferAbortedException
     */
    private void write8(int register, int value) throws TransferAbortedException {
        if (i2c.write(TCS34725_COMMAND_BIT | register, (byte) (value & 0xff)) == true) {
            throw new TransferAbortedException("Write aborted");
        }
        if (verbose) {
            System.out.println("(U8) I2C: Device " + toHex(TCS34725_ADDRESS) + " wrote " + toHex(value) + " to reg "
                    + toHex(~TCS34725_COMMAND_BIT & register));
        }
    }

    /**
     * Read an unsigned 16-bit interger
     * 
     * @param register Register to read from
     * @return Value read
     * @throws TransferAbortedException
     */
    private int readU16(int register) throws TransferAbortedException {
        ByteBuffer rawByte = ByteBuffer.allocate(2);
        if (i2c.read(TCS34725_COMMAND_BIT | TCS34725_COMMAND_AUTO_INCREMENT | register, 2, rawByte) == true) {
            throw new TransferAbortedException("Read aborted");
        }
        byte lo = rawByte.get();
        byte hi = rawByte.get();

        int result = ((hi & 0xFF) << 8) | (lo & 0xFF);
        if (verbose) {
            System.out.println("(U16) I2C: Device " + toHex(TCS34725_ADDRESS) + " returned " + toHex(result)
                    + " from reg " + toHex(~TCS34725_COMMAND_BIT & register));
        }
        return result;
    }

    /**
     * Read an unsigned byte from the I2C device
     * 
     * @param reg Register to read from
     * @return Result read
     * @throws TransferAbortedException
     */
    private int readU8(int reg) throws TransferAbortedException {
        int result = 0;
        ByteBuffer rawByte = ByteBuffer.allocate(1);
        if (i2c.read(TCS34725_COMMAND_BIT | reg, 1, rawByte) == true) {
            throw new TransferAbortedException("Read aborted");
        }
        result = rawByte.get() & 0xFF;
        if (verbose) {
            System.out.println("(U8) I2C: Device " + toHex(TCS34725_ADDRESS) + " returned " + toHex(result)
                    + " from reg " + toHex(~TCS34725_COMMAND_BIT & reg));
        }
        return result;
    }

    private static String toHex(int i) {
        String s = Integer.toString(i, 16).toUpperCase();
        while (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return "0x" + s;
    }

    public String getBallColor() {
        String retVal = "UNKNOWN";

        try {
            TCS34725_Values colorVaues = getRawData();
            double blue = colorVaues.getB();
            double redOverBlue = colorVaues.getR() / blue;

            if (redOverBlue > 3) {
                retVal = "RED";
            } else if (redOverBlue < 1) {
                retVal = "BLUE";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public static class TransferAbortedException extends Exception {
        public TransferAbortedException(String message) {
            super(message);
        }
    }

}
