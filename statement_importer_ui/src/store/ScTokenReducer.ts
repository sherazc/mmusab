import { emptyScToken, ScToken } from "../types/types";


export enum ScTokenActionType {
    login = "SC_AUTH_LOGIN",
    logout = "SC_AUTH_LOGIN",
}

export type ScTokenAction = {
    type: ScTokenActionType,
    payload?: ScToken
};

export const scTokenReducer = (scToken: ScToken, action: ScTokenAction): ScToken => {
    switch (action.type) {
        case ScTokenActionType.login:
            return action.payload ? action.payload: emptyScToken();
        case ScTokenActionType.logout:
            return emptyScToken();
        default:
            return scToken;
    }
};
