package com.openraildata;

import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken;
import com.thalesgroup.rtti._2017_10_01.ldb.GetBoardRequestParams;
import com.thalesgroup.rtti._2017_10_01.ldb.Ldb;
import com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.util.List;

/**
 * Open Live Departure Boards Web Service (OpenLDBWS) API Demonstrator
 * Copyright (C)2018-2021 OpenTrainTimes Ltd.
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

    private static final Logger logger = LoggerFactory.getLogger(GetDepartureBoardExample.class);

    private static final String LDB_TOKEN = "";
    private static final boolean DEBUG = false;

    public static void main(String[] args) throws ConfigurationException {

        if (LDB_TOKEN.isEmpty()) {
            throw new ConfigurationException("Please configure your OpenLDBWS token in GetDepartureBoardExample!");
        }

        var accessToken = new AccessToken();
        accessToken.setTokenValue(LDB_TOKEN);

        var soap = new Ldb();
        var soapService = soap.getLDBServiceSoap12();

        /*
         * To examine the request and responses sent to the service, set DEBUG to true above
         */
        if (DEBUG) {
            var client = ClientProxy.getClient(soapService);
            client.getInInterceptors().add(new LoggingInInterceptor());
            client.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        var params = new GetBoardRequestParams();
        params.setCrs("EUS");

        StationBoardResponseType departureBoard = soapService.getDepartureBoard(params, accessToken);

        logger.info("Trains at {}", departureBoard.getGetStationBoardResult().getLocationName());
        logger.info("===============================================================================");

        List<ServiceItem> service = departureBoard.getGetStationBoardResult().getTrainServices().getService();

        for (ServiceItem si : service) {

            logger.info("{} to {} - {}", si.getStd(), si.getDestination().getLocation().get(0).getLocationName(), si.getEtd());

        }

    }

}
