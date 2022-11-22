///////////////////////////////////////////////////////////////////////////////////
// The following FIT Protocol software provided may be used with FIT protocol
// devices only and remains the copyrighted property of Garmin International, Inc.
// The software is being provided on an "as-is" basis and as an accommodation,
// and therefore all warranties, representations, or guarantees of any kind
// (whether express, implied or statutory) including, without limitation,
// warranties of merchantability, non-infringement, or fitness for a particular
// purpose, are specifically disclaimed.
//
// Copyright 2022 Garmin International, Inc.
///////////////////////////////////////////////////////////////////////////////////
// ****WARNING****  This file is auto-generated!  Do NOT edit this file.
// Profile Version = 21.89Release
// Tag = production/akw/21.89.00-0-g487f2ed0
///////////////////////////////////////////////////////////////////////////////////


package com.leviatan.fit.examples;

import com.leviatan.fit.*;
import com.leviatan.fit.csv.MesgCSVWriter;

import java.io.FileInputStream;

public class MonitoringReaderExample implements MonitoringMesgListener  {
   private MesgCSVWriter mesgWriter;

   public static void main(String[] args) {
      MonitoringReaderExample example = new MonitoringReaderExample();
      String inputFile;
      int interval;
      System.out.printf("FIT Monitoring Reader Example Application - Protocol %d.%d Profile %.2f %s\n", Fit.PROTOCOL_VERSION_MAJOR, Fit.PROTOCOL_VERSION_MINOR, Fit.PROFILE_VERSION / 100.0, Fit.PROFILE_TYPE);
      if (args.length != 2) {
         System.out
               .println("Usage: java -jar MonitoringReaderExample.jar <input file>.fit <interval>");
         return;
      }

      inputFile = args[0];
      interval = Integer.parseInt(args[1]);

      example.Process(inputFile, interval, false);

      if (interval == MonitoringReader.DAILY_INTERVAL) {
         example.Process(inputFile, interval, true);
      }

      System.out.println("Decoded monitoring data from " + args[0] + " to at " + args[1] + "s intervals.");
   }

   public void Process(String inputFile, int interval, boolean dailyTotals)
   {
      Decode decode;
      MonitoringReader monitoringReader;
      MesgBroadcaster mesgBroadcaster;
      FileInputStream inputStream;

      try {
         inputStream = new FileInputStream(inputFile);
      } catch (java.io.IOException e) {
         throw new RuntimeException("Error opening file " + inputFile);
      }


      decode = new Decode();
      mesgBroadcaster = new MesgBroadcaster(decode);
      monitoringReader = new MonitoringReader(interval);
      if (dailyTotals) {
         monitoringReader.outputDailyTotals();
      }

      mesgBroadcaster.addListener((MonitoringInfoMesgListener) monitoringReader);
      mesgBroadcaster.addListener((MonitoringMesgListener) monitoringReader);
      mesgBroadcaster.addListener((DeviceSettingsMesgListener) monitoringReader);
      mesgBroadcaster.addListener((FileIdMesgListener) monitoringReader);
      monitoringReader.addListener(this);

      try {
         String outputFile = inputFile + "-f";

         if (dailyTotals) {
            outputFile += "-dailyTotals";
         } else {
            outputFile += "-i" + interval;
         }

         outputFile += ".csv";

         mesgWriter = new MesgCSVWriter(outputFile);
         mesgWriter.showInvalidsAsEmptyCells();

         try {
            mesgBroadcaster.run(inputStream); // Run decoder.
         } catch (FitRuntimeException a) {
            // If a file with 0 data size in it's header  has been encountered,
            // attempt to keep processing the file
            if (decode.getInvalidFileDataSize()) {
               decode.nextFile();
               mesgBroadcaster.run(inputStream);
            }
         }
         monitoringReader.broadcast(); // End of file so flush pending data.
         mesgWriter.close();
      } catch (FitRuntimeException e) {
         System.err.print("Exception decoding file: ");
         System.err.println(e.getMessage());
      }
   }

   public void onMesg(MonitoringMesg mesg) {
      mesgWriter.onMesg(mesg);
   }
}
