package com.leviatan.backend.utils;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;
import com.leviatan.backend.model.analysis.metadata.TrackFrame;
import org.springframework.web.multipart.MultipartFile;
import pl.jakubtrzcinski.tcxparser.TcxParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCXReader {
    private TcxParser parser = new TcxParser();
    private TrainingCenterDatabaseT actualFile;
    private int size;

    public void parse(MultipartFile file) throws Exception {
        actualFile = parser.parseTCX(file.getInputStream());
        size = actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().size();
    }

    public short getBPMMean() {
        return actualFile.getActivities().getActivity().get(0).getLap().get(0).getAverageHeartRateBpm().getValue();
    }

    public List<TrackFrame> getTracklist() {
        List<TrackFrame> frames = new ArrayList<>();
        long zero = actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().get(0).getTime().toGregorianCalendar().getTimeInMillis();
        for (int i = 0; i < size; i++) {
            long diff = actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().get(i).getTime().toGregorianCalendar().getTimeInMillis() - zero;
            frames.add(new TrackFrame(diff, actualFile.getActivities().getActivity().get(0).getLap().get(0).getTrack().get(0).getTrackpoint().get(i).getHeartRateBpm().getValue()));
        }
        return frames;
    }
}
