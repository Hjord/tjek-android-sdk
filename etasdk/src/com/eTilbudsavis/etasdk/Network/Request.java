package com.eTilbudsavis.etasdk.Network;

import com.eTilbudsavis.etasdk.Eta;

public abstract class Request {

	public static final String TAG = "Request";

	/**
	 * Helper class for the parameters the eTilbudsavis API supports.<br>
	 * Note that not all parameters are necessarily in this set.<br><br>
	 * 
	 * For more information on parameters, please read the API documentation at engineering.etilbudsavis.dk/eta-api/
	 * 
	 * @author Danny Hvam - danny@etilbudsavis.dk
	 */
	public static class Param {

		/** String identifying the order by parameter for all list calls to the API */
		public static final String ORDER_BY = "order_by";

		/** API v2 parameter name for sensor. */
		public static final String SENSOR = "r_sensor";

		/** API v2 parameter name for latitude. */
		public static final String LATITUDE = "r_lat";

		/** API v2 parameter name for longitude. */
		public static final String LONGITUDE = "r_lng";

		/** API v2 parameter name for radius. */
		public static final String RADIUS = "r_radius";

		/** API v2 parameter name for bounds east. */
		public static final String BOUND_EAST = "b_east";

		/** API v2 parameter name for bounds north. */
		public static final String BOUND_NORTH = "b_north";

		/** API v2 parameter name for bounds south. */
		public static final String BOUND_SOUTH = "b_south";

		/** API v2 parameter name for bounds west. */
		public static final String BOUND_WEST = "b_west";

		/** API v2 parameter name for API Key */
		public static final String API_KEY = "api_key";

		/** String identifying the offset parameter for all list calls to the API */
		public static final String OFFSET = "offset";

		/** String identifying the limit parameter for all list calls to the API */
		public static final String LIMIT = "limit";

		/** String identifying the run from parameter for all list calls to the API */
		public static final String RUN_FROM = "run_from";

		/** String identifying the run till parameter for all list calls to the API */
		public static final String RUN_TILL = "run_till";

		/** String identifying the color parameter for all list calls to the API */
		public static final String COLOR = "color";

		/** Parameter for pdf file location */
		public static final String PDF = "pdf";

		/** Parameter for a resource name, e.g. dealer name */
		public static final String NAME = "name";

		/** Parameter for a dealer resource */
		public static final String DEALER = "dealer";

		/** Parameter for the friendly name of a website */
		public static final String URL_NAME = "url_name";

		/** Parameter for pageflip color */
		public static final String PAGEFLIP_COLOR = "pageflip_color";

		/** Parameter for the absolute address of a website */
		public static final String WEBSITE = "website";

		/** Parameter for a resource logo */
		public static final String LOGO = "logo";

		/** Parameter for search */
		public static final String QUERY = "query";

		/** Parameter for pageflip logo location */
		public static final String PAGEFLIP_LOGO = "pageflip_Logo";

		/** Parameter for catalog id's */
		public static final String FILTER_CATALOG_IDS = "catalog_ids";

		/** Parameter for store id's */
		public static final String FILTER_STORE_IDS = "store_ids";

		/** Parameter for area id's */
		public static final String FILTER_AREA_IDS = "area_ids";

		/** Parameter for store id's */
		public static final String FILTER_OFFER_IDS = "offer_ids";

		/** Parameter for getting a list of specific dealer id's */
		public static final String FILTER_DEALER_IDS = "dealer_ids";

		/** Parameter for a resource e-mail */
		public static final String EMAIL = "email";

		/** Parameter for a resource password */
		public static final String PASSWORD = "password";

		/** Parameter for a resource birth year */
		public static final String BIRTH_YEAR = "birth_year";

		/** Parameter for a resource gender */
		public static final String GENDER = "gender";

		/** Parameter for a resource success redirect */
		public static final String SUCCESS_REDIRECT = "success_redirect";

		/** Parameter for a resource error redirect */
		public static final String ERROR_REDIRECT = "error_redirect";

		/** Parameter for a resource old password */
		public static final String OLD_PASSWORD = "old_password";

		/** Parameter for a facebook token */
		public static final String FACEBOOK_TOKEN = "facebook_token";

		/** Parameter for a delete filter */
		public static final String FILTER_DELETE = "filter";

		public static final String ID = "id";
		
		public static final String MODIFIED = "modified";
		
		public static final String ERN = "ern";
		
		public static final String ACCESS = "access";
		
		public static final String ACCEPT_URL = "accept_url";

		public static final String DESCRIPTION = "description";
		
		public static final String COUNT = "count";
		
		public static final String TICK = "tick";
		
		public static final String OFFER_ID = "offer_id";
		
		public static final String CREATOR = "creator";
		
		public static final String SHOPPING_LIST_ID = "shopping_list_id";

		/** Parameter for a resource token time to live */
		public static final String TOKEN_TTL = "token_ttl";

		/** Parameter for a v1 session migration */
		public static final String V1_AUTH_ID = "v1_auth_id";
	
		/** Parameter for a v1 session migration */
		public static final String V1_AUTH_TIME = "v1_auth_time";
	
		/** Parameter for a v1 session migration */
		public static final String V1_AUTH_HASH = "v1_auth_hash";
		
	}

	/**
	 * Helper class for headers the eTilbudsavis API uses
	 * @author Danny Hvam - danny@etilbudsavis.dk
	 */
	public static class Header {

		/** Header name for the session token */
		public static final String X_TOKEN = "X-Token";

		/** Header name for the session expire token */
		public static final String X_TOKEN_EXPIRES = "X-Token-Expires";

		/** Header name for the signature */
		public static final String X_SIGNATURE = "X-Signature";

		/** Header name for content_type */
		public static final String CONTENT_TYPE = "Content-Type";

		/** Header name for content_type */
		public static final String RETRY_AFTER = "Retry-After";

	}

	/**
	 * Helper class for the sort orders the eTilbudsavis API supports.<br>
	 * These are typically used for requests to any list endpoint.<br>
	 * Note that not all parameters are necessarily in this set.<br><br>
	 * 
	 * For more information on parameters, please read the API documentation at engineering.etilbudsavis.dk/eta-api/
	 * 
	 * @author Danny Hvam - danny@etilbudsavis.dk
	 */
	public static class Sort {

		/** String identifying the order by parameter for all list calls to the API */
		public static final String ORDER_BY = "order_by";

		/** String identifying the descending variable */
		public static final String DESC = "-";

		/** Sort a list by popularity in ascending order. (smallest to largest) */
		public static final String POPULARITY = "popularity";

		/** Sort a list by distance in ascending order. (smallest to largest) */
		public static final String DISTANCE = "distance";

		/** Sort a list by name in ascending order. (a-z) */
		public static final String NAME = "name";

		/** Sort a list by published in ascending order. (smallest to largest) */
		public static final String PUBLISHED = "published";

		/** Sort a list by expired in ascending order. (smallest to largest) */
		public static final String EXPIRED = "expired";

		/** Sort a list by created in ascending order. (smallest to largest) */
		public static final String CREATED = "created";

		/** Sort a list by page (in catalog) in ascending order. (smallest to largest) */
		public static final String PAGE = "page";

		/** Sort a list by popularity in descending order. (largest to smallest)*/
		public static final String POPULARITY_DESC = DESC + POPULARITY;

		/** Sort a list by distance in descending order. (largest to smallest)*/
		public static final String DISTANCE_DESC = DESC + DISTANCE;

		/** Sort a list by name in descending order. (z-a)*/
		public static final String NAME_DESC = DESC + NAME;

		/** Sort a list by published in descending order. (largest to smallest)*/
		public static final String PUBLISHED_DESC = DESC + PUBLISHED;

		/** Sort a list by expired in descending order. (largest to smallest)*/
		public static final String EXPIRED_DESC = DESC + EXPIRED;

		/** Sort a list by created in ascending order. (smallest to largest) */
		public static final String CREATED_DESC = DESC + CREATED;

		/** Sort a list by page (in catalog) in descending order. (largest to smallest)*/
		public static final String PAGE_DESC = DESC + PAGE;

	}

	/**
	 * @author Danny Hvam - danny@etilbudsavis.dk
	 */
	public static class Endpoint {
		
		
		
		// GLOBALS
		public static final String PRODUCTION = "https://api.etilbudsavis.dk";
		public static final String EDGE = "https://edge.etilbudsavis.dk";
		public static final String STAGING = "https://staging.etilbudsavis.dk";

		// LISTS
		public static final String CATALOG_LIST = "/v2/catalogs";
		public static final String CATALOG_ID = "/v2/catalogs/";
		public static final String CATALOG_SEARCH = "/v2/catalogs/search";
		
		public static final String DEALER_LIST = "/v2/dealers";
		public static final String DEALER_ID = "/v2/dealers/";
		public static final String DEALER_SEARCH = "/v2/dealers/search";
		
		public static final String OFFER_LIST = "/v2/offers";
		public static final String OFFER_ID = "/v2/offers/";
		public static final String OFFER_SEARCH = "/v2/offers/search";
		public static final String OFFER_TYPEAHEAD = "/v2/offers/typeahead";
		
		public static final String STORE_LIST = "/v2/stores";
		public static final String STORE_ID = "/v2/stores/";
		public static final String STORE_SEARCH = "/v2/stores/search";
		public static final String STORE_QUICK_SEARCH = "/v2/stores/quicksearch";
		
		public static final String SESSIONS = "/v2/sessions";
		
		public static final String USER_ID = "/v2/users/";
		public static final String USER_RESET = "/v2/users/reset";
		
		public static final String CATEGORIES	= "/v2/categories";
		
		
		public static String getHost() {
			return Eta.DEBUG_LOGD ? EDGE : PRODUCTION;
		}

		/**
		 * /v2/offers/{offer_id}/collect
		 */
		public static String offerCollect(String offerId) {
			return String.format("/v2/offers/%s/collect", offerId);
		}

		/**
		 * /v2/stores/{offer_id}/collect
		 */
		public static String storeCollect(String storeId) {
			return String.format("/v2/stores/%s/collect", storeId);
		}
		
		/**
		 * https://etilbudsavis.dk/proxy/{id}/
		 */
		public static String pageflipProxy(String id) {
			String production = "https://etilbudsavis.dk/proxy/%s/";
			String staging = "http://10.0.1.6:3000/proxy/%s/";
//			String staging = "https://staging.etilbudsavis.dk/proxy/%s/";
			return String.format(Eta.DEBUG_PAGEFLIP ? staging : production, id);
		}
		
		/**
		 * https://staging.etilbudsavis.dk/utils/ajax/lists/themes/
		 */
		public static String themes() {
			String production = "https://etilbudsavis.dk/utils/ajax/lists/themes/";
			String staging = "https://staging.etilbudsavis.dk/utils/ajax/lists/themes/";
			return production;
		}
		
		/**
		 * /v2/users/{user_id}/facebook
		 */
		public static String facebook(int userId) {
			return String.format("/v2/users/%s/facebook", userId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists
		 */
		public static String lists(int userId) {
			return String.format("/v2/users/%s/shoppinglists", userId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}
		 * @param userId
		 * @param listId
		 * @return
		 */
		public static String list(int userId, String listId) {
			return String.format("/v2/users/%s/shoppinglists/%s", userId, listId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/modified
		 */
		public static String listModified(int userId, String listId) {
			return String.format("/v2/users/%s/shoppinglists/%s/modified", userId, listId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/empty
		 */
		public static String listEmpty(int userId, String listId) {
			return String.format("/v2/users/%s/shoppinglists/%s/empty", userId, listId);
		}
		
		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/shares
		 * @param userId
		 * @param listId
		 * @return
		 */
		public static String listShares(int userId, String listId) {
			return String.format("/v2/users/%s/shoppinglists/%s/shares", userId, listId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/shares/{email}
		 */
		public static String listShareEmail(int userId, String listId, String email) {
			return String.format("/v2/users/%s/shoppinglists/%s/shares/%s", userId, listId, email);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/items
		 */
		public static String items(int userId, String listId) {
			return String.format("/v2/users/%s/shoppinglists/%s/items", userId, listId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/items/{item_uuid}
		 */
		public static String item(int userId, String listId, String itemId) {
			return String.format("/v2/users/%s/shoppinglists/%s/items/%s", userId, listId, itemId);
		}

		/**
		 * /v2/users/{user_id}/shoppinglists/{list_uuid}/items/{item_uuid}/modified
		 */
		public static String itemModifiedById(int userId, String listId, String itemId) {
			return String.format("/v2/users/%s/shoppinglists/%s/items/%s/modified", userId, listId, itemId);
		}

	}
}