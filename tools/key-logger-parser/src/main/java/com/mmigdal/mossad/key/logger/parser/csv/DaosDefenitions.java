package com.mmigdal.mossad.key.logger.parser.csv;

public class DaosDefenitions {

      public static final String DAO_CHARGE_POINT_STATUS = "ChargePointStatusDao";
      public static final String DAO_CHARGE_POINT_STATUS_upsertOnline = "upsertOnline";
      public static final String STATUS_VIEW_DAO = "StatusViewDao";
      public static final String STATUS_VIEW_DAO_findByChargePointId = "findByChargePointId";
      public static final String STATUS_VIEW_DAO_findByConnectorId = "findByConnectorId";
      public static final String DAO_CONNECTOR_STATUS = "ConnectorStatusDao";
      public static final String DAO_CIRCUIT_BREAKER = "CircuitBreakerDao";
      public static final String DAO_CONNECTOR_ERROR = "ConnectorErrorDao";
      public static final String PUBLISHING_SERVICE = "PublishingService";
      public static final String PUBLISHING_SERVICE_publishEvent = "publishEvent";
      public static final String findByChargePointAndConnectorId = "findByChargePointAndConnectorId";
      public static final String insertStatusNotification = "insertStatusNotification";
      public static final String deleteByConnectorId = "deleteByConnectorId";

      private DaosDefenitions() {
      }

}
