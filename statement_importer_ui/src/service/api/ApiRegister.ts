import {
    addHeadersInRequest,
    ApiHeaders,
    ApiRequest,
    callApiIntercept,
    InterceptorCallBacks
} from "./ApiCore";
import {parseObjectsIsoDateToMdDate} from "../DateService";
import { LoginRequest, ScToken } from "../../types/types";

export const baseUrl = process.env.REACT_APP_API_BASE_PATH;

// const CONTENT_JSON_HEADER: ApiHeaders = [["Content-Type", "application/json"]]
const CONTENT_JSON_HEADER = (): ApiHeaders => [["Content-Type", "application/json"]]

/**
 * This method creates all the available endpoints.
 */
export const registerEndpoints = () => {
    return {
        epLoginToken: () => `${baseUrl}/api/login/token?requestedScopes=ROLE_USER&requestedScopes=ROLE_ADMIN&requestedScopes=READ&requestedScopes=WRITE`
    }
}

/**
 * Setup all EVENT Register endpoints.
 *
 * TODO: create and export instead of creating it different places.
 */
export const registerApis = (commonHeaders?: ApiHeaders, interceptorCbs?: InterceptorCallBacks) => {

    const endpoints = registerEndpoints();

    const api = {
        login: (loginRequest: LoginRequest): Promise<ScToken> => {
            const endpoint = endpoints.epLoginToken();
            const request: ApiRequest = {endpoint};

            const encodedUserPassword =
                btoa(`${loginRequest.userName}:${loginRequest.userPassword}`);

            const authenticationHeaders: ApiHeaders = [["Authorization", `Basic ${encodedUserPassword}`]];

            // Removed because it adds Bearer Authorization header
            // addHeadersInRequest(request, commonHeaders);
            addHeadersInRequest(request, authenticationHeaders);

            return callApiIntercept(request, interceptorCbs);
        }

    }
    return api;
}

const addMdDateParserInterceptor = (interceptorCbs?: InterceptorCallBacks): InterceptorCallBacks => {
    let interceptor: InterceptorCallBacks = interceptorCbs ? interceptorCbs : {};
    interceptor.afterSuccess = interceptor.afterSuccess ? interceptor.afterSuccess : [];
    interceptor.afterError = interceptor.afterError ? interceptor.afterError : [];
    const containsParser = interceptor.afterSuccess.find(fn => fn == parseObjectsIsoDateToMdDate);
    if (!containsParser) {
        interceptor.afterSuccess.push(parseObjectsIsoDateToMdDate)
    }
    return interceptor;
}


export const createAuthHeader = (authUserTokenDto: ScToken): ApiHeaders => ([[
    "Authorization", `Bearer ${authUserTokenDto.token}`
]]);

