package com.leviatan.backend.utils;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;
import pl.jakubtrzcinski.tcxparser.TcxParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCXReader {
    private TcxParser parser = new TcxParser();
    private TrainingCenterDatabaseT actualFile;
    private int size;

    public void parse(File file) throws Exception {
        actualFile = parser.parseTCX(new FileInputStream(file));
        size = actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().size();
    }

    public short getBPMMean() {
        return actualFile.getActivities().getActivity().get(0).getLap().get(0).getAverageHeartRateBpm().getValue();
    }

    public List<Short> getBPMTracklist() {
        List<Short> bpmList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bpmList.add(actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().get(i).getHeartRateBpm().getValue());
        }
        return bpmList;
    }

    public List<Date> getTimeTracklist() {
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            dateList.add(actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().get(i).getTime().toGregorianCalendar().getTime());
        }
        return dateList;
    }
}
