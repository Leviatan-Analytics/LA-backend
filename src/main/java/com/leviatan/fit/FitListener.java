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

package com.leviatan.fit;

public class FitListener implements MesgListener {

    protected final FitMessages fitMessages = new FitMessages();

    public FitListener() {

    }

    public FitMessages getFitMessages() {
        return fitMessages;
    }

    @Override
    public void onMesg(Mesg mesg) {
        switch (mesg.getNum()) {
            case MesgNum.FILE_ID:
                fitMessages.fileIdMesgs.add(new FileIdMesg(mesg));
                break;
            case MesgNum.FILE_CREATOR:
                fitMessages.fileCreatorMesgs.add(new FileCreatorMesg(mesg));
                break;
            case MesgNum.TIMESTAMP_CORRELATION:
                fitMessages.timestampCorrelationMesgs.add(new TimestampCorrelationMesg(mesg));
                break;
            case MesgNum.SOFTWARE:
                fitMessages.softwareMesgs.add(new SoftwareMesg(mesg));
                break;
            case MesgNum.SLAVE_DEVICE:
                fitMessages.slaveDeviceMesgs.add(new SlaveDeviceMesg(mesg));
                break;
            case MesgNum.CAPABILITIES:
                fitMessages.capabilitiesMesgs.add(new CapabilitiesMesg(mesg));
                break;
            case MesgNum.FILE_CAPABILITIES:
                fitMessages.fileCapabilitiesMesgs.add(new FileCapabilitiesMesg(mesg));
                break;
            case MesgNum.MESG_CAPABILITIES:
                fitMessages.mesgCapabilitiesMesgs.add(new MesgCapabilitiesMesg(mesg));
                break;
            case MesgNum.FIELD_CAPABILITIES:
                fitMessages.fieldCapabilitiesMesgs.add(new FieldCapabilitiesMesg(mesg));
                break;
            case MesgNum.DEVICE_SETTINGS:
                fitMessages.deviceSettingsMesgs.add(new DeviceSettingsMesg(mesg));
                break;
            case MesgNum.USER_PROFILE:
                fitMessages.userProfileMesgs.add(new UserProfileMesg(mesg));
                break;
            case MesgNum.HRM_PROFILE:
                fitMessages.hrmProfileMesgs.add(new HrmProfileMesg(mesg));
                break;
            case MesgNum.SDM_PROFILE:
                fitMessages.sdmProfileMesgs.add(new SdmProfileMesg(mesg));
                break;
            case MesgNum.BIKE_PROFILE:
                fitMessages.bikeProfileMesgs.add(new BikeProfileMesg(mesg));
                break;
            case MesgNum.CONNECTIVITY:
                fitMessages.connectivityMesgs.add(new ConnectivityMesg(mesg));
                break;
            case MesgNum.WATCHFACE_SETTINGS:
                fitMessages.watchfaceSettingsMesgs.add(new WatchfaceSettingsMesg(mesg));
                break;
            case MesgNum.OHR_SETTINGS:
                fitMessages.ohrSettingsMesgs.add(new OhrSettingsMesg(mesg));
                break;
            case MesgNum.ZONES_TARGET:
                fitMessages.zonesTargetMesgs.add(new ZonesTargetMesg(mesg));
                break;
            case MesgNum.SPORT:
                fitMessages.sportMesgs.add(new SportMesg(mesg));
                break;
            case MesgNum.HR_ZONE:
                fitMessages.hrZoneMesgs.add(new HrZoneMesg(mesg));
                break;
            case MesgNum.SPEED_ZONE:
                fitMessages.speedZoneMesgs.add(new SpeedZoneMesg(mesg));
                break;
            case MesgNum.CADENCE_ZONE:
                fitMessages.cadenceZoneMesgs.add(new CadenceZoneMesg(mesg));
                break;
            case MesgNum.POWER_ZONE:
                fitMessages.powerZoneMesgs.add(new PowerZoneMesg(mesg));
                break;
            case MesgNum.MET_ZONE:
                fitMessages.metZoneMesgs.add(new MetZoneMesg(mesg));
                break;
            case MesgNum.DIVE_SETTINGS:
                fitMessages.diveSettingsMesgs.add(new DiveSettingsMesg(mesg));
                break;
            case MesgNum.DIVE_ALARM:
                fitMessages.diveAlarmMesgs.add(new DiveAlarmMesg(mesg));
                break;
            case MesgNum.DIVE_GAS:
                fitMessages.diveGasMesgs.add(new DiveGasMesg(mesg));
                break;
            case MesgNum.GOAL:
                fitMessages.goalMesgs.add(new GoalMesg(mesg));
                break;
            case MesgNum.ACTIVITY:
                fitMessages.activityMesgs.add(new ActivityMesg(mesg));
                break;
            case MesgNum.SESSION:
                fitMessages.sessionMesgs.add(new SessionMesg(mesg));
                break;
            case MesgNum.LAP:
                fitMessages.lapMesgs.add(new LapMesg(mesg));
                break;
            case MesgNum.LENGTH:
                fitMessages.lengthMesgs.add(new LengthMesg(mesg));
                break;
            case MesgNum.RECORD:
                fitMessages.recordMesgs.add(new RecordMesg(mesg));
                break;
            case MesgNum.EVENT:
                fitMessages.eventMesgs.add(new EventMesg(mesg));
                break;
            case MesgNum.DEVICE_INFO:
                fitMessages.deviceInfoMesgs.add(new DeviceInfoMesg(mesg));
                break;
            case MesgNum.DEVICE_AUX_BATTERY_INFO:
                fitMessages.deviceAuxBatteryInfoMesgs.add(new DeviceAuxBatteryInfoMesg(mesg));
                break;
            case MesgNum.TRAINING_FILE:
                fitMessages.trainingFileMesgs.add(new TrainingFileMesg(mesg));
                break;
            case MesgNum.WEATHER_CONDITIONS:
                fitMessages.weatherConditionsMesgs.add(new WeatherConditionsMesg(mesg));
                break;
            case MesgNum.WEATHER_ALERT:
                fitMessages.weatherAlertMesgs.add(new WeatherAlertMesg(mesg));
                break;
            case MesgNum.GPS_METADATA:
                fitMessages.gpsMetadataMesgs.add(new GpsMetadataMesg(mesg));
                break;
            case MesgNum.CAMERA_EVENT:
                fitMessages.cameraEventMesgs.add(new CameraEventMesg(mesg));
                break;
            case MesgNum.GYROSCOPE_DATA:
                fitMessages.gyroscopeDataMesgs.add(new GyroscopeDataMesg(mesg));
                break;
            case MesgNum.ACCELEROMETER_DATA:
                fitMessages.accelerometerDataMesgs.add(new AccelerometerDataMesg(mesg));
                break;
            case MesgNum.MAGNETOMETER_DATA:
                fitMessages.magnetometerDataMesgs.add(new MagnetometerDataMesg(mesg));
                break;
            case MesgNum.BAROMETER_DATA:
                fitMessages.barometerDataMesgs.add(new BarometerDataMesg(mesg));
                break;
            case MesgNum.THREE_D_SENSOR_CALIBRATION:
                fitMessages.threeDSensorCalibrationMesgs.add(new ThreeDSensorCalibrationMesg(mesg));
                break;
            case MesgNum.ONE_D_SENSOR_CALIBRATION:
                fitMessages.oneDSensorCalibrationMesgs.add(new OneDSensorCalibrationMesg(mesg));
                break;
            case MesgNum.VIDEO_FRAME:
                fitMessages.videoFrameMesgs.add(new VideoFrameMesg(mesg));
                break;
            case MesgNum.OBDII_DATA:
                fitMessages.obdiiDataMesgs.add(new ObdiiDataMesg(mesg));
                break;
            case MesgNum.NMEA_SENTENCE:
                fitMessages.nmeaSentenceMesgs.add(new NmeaSentenceMesg(mesg));
                break;
            case MesgNum.AVIATION_ATTITUDE:
                fitMessages.aviationAttitudeMesgs.add(new AviationAttitudeMesg(mesg));
                break;
            case MesgNum.VIDEO:
                fitMessages.videoMesgs.add(new VideoMesg(mesg));
                break;
            case MesgNum.VIDEO_TITLE:
                fitMessages.videoTitleMesgs.add(new VideoTitleMesg(mesg));
                break;
            case MesgNum.VIDEO_DESCRIPTION:
                fitMessages.videoDescriptionMesgs.add(new VideoDescriptionMesg(mesg));
                break;
            case MesgNum.VIDEO_CLIP:
                fitMessages.videoClipMesgs.add(new VideoClipMesg(mesg));
                break;
            case MesgNum.SET:
                fitMessages.setMesgs.add(new SetMesg(mesg));
                break;
            case MesgNum.JUMP:
                fitMessages.jumpMesgs.add(new JumpMesg(mesg));
                break;
            case MesgNum.CLIMB_PRO:
                fitMessages.climbProMesgs.add(new ClimbProMesg(mesg));
                break;
            case MesgNum.FIELD_DESCRIPTION:
                fitMessages.fieldDescriptionMesgs.add(new FieldDescriptionMesg(mesg));
                break;
            case MesgNum.DEVELOPER_DATA_ID:
                fitMessages.developerDataIdMesgs.add(new DeveloperDataIdMesg(mesg));
                break;
            case MesgNum.COURSE:
                fitMessages.courseMesgs.add(new CourseMesg(mesg));
                break;
            case MesgNum.COURSE_POINT:
                fitMessages.coursePointMesgs.add(new CoursePointMesg(mesg));
                break;
            case MesgNum.SEGMENT_ID:
                fitMessages.segmentIdMesgs.add(new SegmentIdMesg(mesg));
                break;
            case MesgNum.SEGMENT_LEADERBOARD_ENTRY:
                fitMessages.segmentLeaderboardEntryMesgs.add(new SegmentLeaderboardEntryMesg(mesg));
                break;
            case MesgNum.SEGMENT_POINT:
                fitMessages.segmentPointMesgs.add(new SegmentPointMesg(mesg));
                break;
            case MesgNum.SEGMENT_LAP:
                fitMessages.segmentLapMesgs.add(new SegmentLapMesg(mesg));
                break;
            case MesgNum.SEGMENT_FILE:
                fitMessages.segmentFileMesgs.add(new SegmentFileMesg(mesg));
                break;
            case MesgNum.WORKOUT:
                fitMessages.workoutMesgs.add(new WorkoutMesg(mesg));
                break;
            case MesgNum.WORKOUT_SESSION:
                fitMessages.workoutSessionMesgs.add(new WorkoutSessionMesg(mesg));
                break;
            case MesgNum.WORKOUT_STEP:
                fitMessages.workoutStepMesgs.add(new WorkoutStepMesg(mesg));
                break;
            case MesgNum.EXERCISE_TITLE:
                fitMessages.exerciseTitleMesgs.add(new ExerciseTitleMesg(mesg));
                break;
            case MesgNum.SCHEDULE:
                fitMessages.scheduleMesgs.add(new ScheduleMesg(mesg));
                break;
            case MesgNum.TOTALS:
                fitMessages.totalsMesgs.add(new TotalsMesg(mesg));
                break;
            case MesgNum.WEIGHT_SCALE:
                fitMessages.weightScaleMesgs.add(new WeightScaleMesg(mesg));
                break;
            case MesgNum.BLOOD_PRESSURE:
                fitMessages.bloodPressureMesgs.add(new BloodPressureMesg(mesg));
                break;
            case MesgNum.MONITORING_INFO:
                fitMessages.monitoringInfoMesgs.add(new MonitoringInfoMesg(mesg));
                break;
            case MesgNum.MONITORING:
                fitMessages.monitoringMesgs.add(new MonitoringMesg(mesg));
                break;
            case MesgNum.HR:
                fitMessages.hrMesgs.add(new HrMesg(mesg));
                break;
            case MesgNum.STRESS_LEVEL:
                fitMessages.stressLevelMesgs.add(new StressLevelMesg(mesg));
                break;
            case MesgNum.MEMO_GLOB:
                fitMessages.memoGlobMesgs.add(new MemoGlobMesg(mesg));
                break;
            case MesgNum.ANT_CHANNEL_ID:
                fitMessages.antChannelIdMesgs.add(new AntChannelIdMesg(mesg));
                break;
            case MesgNum.ANT_RX:
                fitMessages.antRxMesgs.add(new AntRxMesg(mesg));
                break;
            case MesgNum.ANT_TX:
                fitMessages.antTxMesgs.add(new AntTxMesg(mesg));
                break;
            case MesgNum.EXD_SCREEN_CONFIGURATION:
                fitMessages.exdScreenConfigurationMesgs.add(new ExdScreenConfigurationMesg(mesg));
                break;
            case MesgNum.EXD_DATA_FIELD_CONFIGURATION:
                fitMessages.exdDataFieldConfigurationMesgs.add(new ExdDataFieldConfigurationMesg(mesg));
                break;
            case MesgNum.EXD_DATA_CONCEPT_CONFIGURATION:
                fitMessages.exdDataConceptConfigurationMesgs.add(new ExdDataConceptConfigurationMesg(mesg));
                break;
            case MesgNum.DIVE_SUMMARY:
                fitMessages.diveSummaryMesgs.add(new DiveSummaryMesg(mesg));
                break;
            case MesgNum.HRV:
                fitMessages.hrvMesgs.add(new HrvMesg(mesg));
                break;
            case MesgNum.PAD:
                fitMessages.padMesgs.add(new PadMesg(mesg));
                break;
            default:
                break;
        }
    }
}