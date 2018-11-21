package com.openraildata;

import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken;
import com.thalesgroup.rtti._2017_10_01.ldb.GetBoardRequestParams;
import com.thalesgroup.rtti._2017_10_01.ldb.LDBServiceSoap;
import com.thalesgroup.rtti._2017_10_01.ldb.Ldb;
import com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

import java.util.List;

/**
 * Open Live Departure Boards Web Service (OpenLDBWS) API Demonstrator
 * Copyright (C)2018 OpenTrainTimes Ltd.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class GetDepartureBoardExample {

    private static final String LDB_TOKEN = "";

    public static void main(String[] args) {

        if (LDB_TOKEN.isEmpty()) {
            throw new RuntimeException("Please configure your OpenLDBWS token in GetDepartureBoardExample!");
        }

        AccessToken accessToken = new AccessToken();
        accessToken.setTokenValue(LDB_TOKEN);

        Ldb soap = new Ldb();
        LDBServiceSoap soapService = soap.getLDBServiceSoap12();

        GetBoardRequestParams params = new GetBoardRequestParams();
        params.setCrs("EUS");

        StationBoardResponseType departureBoard = soapService.getDepartureBoard(params, accessToken);

        System.out.println("Trains at " + departureBoard.getGetStationBoardResult().getLocationName());
        System.out.println("===============================================================================");

        List<ServiceItem> service = departureBoard.getGetStationBoardResult().getTrainServices().getService();

        for (ServiceItem si : service) {

            System.out.println(si.getStd() + " to " + si.getDestination().getLocation().get(0).getLocationName() + " - " + si.getEtd());

        }

    }

}
