package com.mmigdal.mossad.key.logger.parser.csv;

import java.util.List;

public class DaosAndDefinitions {

      public static final String CHARGE_POINT_STATUS_DAO = "ChargePointStatusDao";
      public static final String CHARGE_POINT_STATUS_DAO_upsertOnline = "upsertOnline";
      public static final String CHARGE_POINT_STATUS_DAO_deleteByChargePointId = "deleteByChargePointId";
      public static final String CHARGE_POINT_STATUS_DAO_markAsDeleted = "markAsDeleted";
      public static final String CHARGE_POINT_STATUS_DAO_findById = "findById";
      public static final String CHARGE_POINT_STATUS_DAO_insert = "insert";
      public static final String CHARGE_POINT_STATUS_DAO_update = "update";
      public static final String CHARGE_POINT_STATUS_DAO_delete = "delete";
      public static final String CHARGE_POINT_STATUS_DAO_upsertStatusNotification = "upsertStatusNotification";
      public static final String CHARGE_POINT_STATUS_DAO_insertOnline = "insertOnline";
      public static final String CHARGE_POINT_STATUS_DAO_updateOnline = "updateOnline";
      public static final String CHARGE_POINT_STATUS_DAO_findChargePointIds = "findChargePointIds";
      public static final String STATUS_LOOKUP_DAO = "StatusLookupDao";

      public static final String STATUS_VIEW_DAO = "StatusViewDao";
      public static final String STATUS_VIEW_DAO_findByChargePointId = "findByChargePointId";
      public static final String STATUS_VIEW_DAO_findByConnectorId = "findByConnectorId";
      public static final String STATUS_LOOKUP_getUpdateQuery = "getUpdateQuery";
      public static final String STATUS_LOOKUP_getInsertQuery = "getInsertQuery";
      public static final String CONNECTOR_STATUS_DAO = "ConnectorStatusDao";
      public static final String CONNECTOR_STATUS_DAO_deleteByChargePointId = "deleteByChargePointId";
      public static final String CONNECTOR_STATUS_DAO_insertStatusNotification = "insertStatusNotification";
      public static final String CONNECTOR_STATUS_DAO_findById = "findById";
      public static final String CONNECTOR_STATUS_DAO_insert = "insert";
      public static final String CONNECTOR_STATUS_DAO_update = "update";
      public static final String CONNECTOR_STATUS_DAO_delete = "delete";
      public static final String CIRCUIT_BREAKER_DAO = "CircuitBreakerDao";
      public static final String CIRCUIT_BREAKER_DAO_findByConnectorId = "findByConnectorId";
      public static final String CIRCUIT_BREAKER_DAO_findByChargePointAndConnectorId = "findByChargePointAndConnectorId";
      public static final String CIRCUIT_BREAKER_DAO_insert = "insert";
      public static final String CIRCUIT_BREAKER_DAO_update = "update";
      public static final String CIRCUIT_BREAKER_DAO_upsert = "upsert";
      public static final String CONNECTOR_ERROR_DAO = "ConnectorErrorDao";
      public static final String CONNECTOR_ERROR_DAO_findById = "findById";
      public static final String CONNECTOR_ERROR_DAO_find = "findBy";
      public static final String CONNECTOR_ERROR_DAO_insert = "insert";
      public static final String CONNECTOR_ERROR_DAO_deleteByConnectorId = "deleteByConnectorId";
      public static final String CHARGE_POINT_ERROR_DAO = "ChargePointErrorDao";
      public static final String CHARGE_POINT_ERROR_DAO_findById = "findById";
      public static final String CHARGE_POINT_ERROR_DAO_find = "find";
      public static final String CHARGE_POINT_ERROR_DAO_insert = "insert";
      public static final String CHARGE_POINT_ERROR_DAO_deleteByChargePointId = "deleteByChargePointId";

      public static final String PUBLISHING_SERVICE = "PublishingService";
      public static final String PUBLISHING_SERVICE_publishEvent = "publishEvent";
      public static List<String> ChargePointStatusDaoMapping = List.of(
              CHARGE_POINT_STATUS_DAO_upsertOnline,
              CHARGE_POINT_STATUS_DAO_deleteByChargePointId,
              CHARGE_POINT_STATUS_DAO_findById,
              CHARGE_POINT_STATUS_DAO_insert,
              CHARGE_POINT_STATUS_DAO_update,
              CHARGE_POINT_STATUS_DAO_delete,
              CHARGE_POINT_STATUS_DAO_upsertStatusNotification,
              CHARGE_POINT_STATUS_DAO_insertOnline,
              CHARGE_POINT_STATUS_DAO_updateOnline,
              CHARGE_POINT_STATUS_DAO_findChargePointIds
      );
      public static List<String> StatusViewDaoMapping = List.of(STATUS_VIEW_DAO_findByChargePointId, STATUS_VIEW_DAO_findByConnectorId);
      public static List<String> StatusLookupDaoMapping = List.of(STATUS_LOOKUP_getUpdateQuery, STATUS_LOOKUP_getInsertQuery);
      public static List<String> ConnectorStatusDaoMapping = List.of(CONNECTOR_STATUS_DAO_deleteByChargePointId, CONNECTOR_STATUS_DAO_insertStatusNotification,
              CONNECTOR_STATUS_DAO_findById, CONNECTOR_STATUS_DAO_insert, CONNECTOR_STATUS_DAO_update, CONNECTOR_STATUS_DAO_delete);
      public static List<String> CircuitBreakerDaoMapping = List.of(CIRCUIT_BREAKER_DAO_findByConnectorId, CIRCUIT_BREAKER_DAO_findByChargePointAndConnectorId,
              CIRCUIT_BREAKER_DAO_insert, CIRCUIT_BREAKER_DAO_update, CIRCUIT_BREAKER_DAO_upsert
      );
      public static List<String> connectorErrorDaoMapping = List.of(CONNECTOR_ERROR_DAO_findById, CONNECTOR_ERROR_DAO_find,
              CONNECTOR_ERROR_DAO_insert, CONNECTOR_ERROR_DAO_deleteByConnectorId
      );
      public static List<String> chargePointErrorDaoMapping = List.of(
              CHARGE_POINT_ERROR_DAO_findById, CHARGE_POINT_ERROR_DAO_find, CHARGE_POINT_ERROR_DAO_insert, CHARGE_POINT_ERROR_DAO_deleteByChargePointId
      );
      public static List<String> publishingServiceMapping = List.of(PUBLISHING_SERVICE_publishEvent);


      private DaosAndDefinitions() {
      }

      public static List<DataExtractor> armExtractors(List<DataExtractor> extractors, String daoName, List<String> mapping) {
            mapping.forEach(
                    item -> {
                          extractors.add(new DataExtractor(daoName, item));
                    }
            );
            return extractors;
      }
}