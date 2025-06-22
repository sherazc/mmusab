import { createContext } from "react";
import { RegisterRolesAction } from "./RegisterRolesReducer";
import { emptyScToken, RegisterRole, ScToken } from "../types/types";
import { ScTokenAction } from "./ScTokenReducer";

type RootStateType = {
    registerRoles: RegisterRole[];
    scToken: ScToken;
}

const initialAppState: RootStateType = {
    registerRoles: [],
    scToken: emptyScToken()
}

type RootAction = RegisterRolesAction | ScTokenAction

const AppContext = createContext<[state: RootStateType, dispatch: React.Dispatch<RootAction>]>
