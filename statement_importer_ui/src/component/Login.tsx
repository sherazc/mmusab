import React, { ChangeEvent, FormEvent, useContext, useReducer, useState } from "react";
import { LoginRequest, ScToken } from "../types/types";
import { AppContext } from "../store/context";

interface Props { }

export const Login: React.FC<Props> = () => {

  const [loginRequest, setLoginRequest] = useState<LoginRequest>({ userName: "", userPassword: "" });
  const [{scToken}, dispatch] = useContext(AppContext);

  const handleInputChange = (element: ChangeEvent<HTMLInputElement>) => {
    setLoginRequest({
      ...loginRequest,
      [element.target.name]: element.target.value
    });
  };

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    apiLogin();
  }


  const apiLogin = async () => {
    const scopes = "requestedScopes=ROLE_USER&requestedScopes=ROLE_ADMIN&requestedScopes=READ&requestedScopes=WRITE";
    const endpoint = "/api/login/token"
    const baseUrl = "http://localhost:8080";

    const credentials = btoa(`${loginRequest.userName}:${loginRequest.userPassword}`);

    const loginRequestUrl = `${baseUrl}${endpoint}?${scopes}`;

    const scTokenResponse = await fetch(loginRequestUrl, {
      method: "GET",
      headers: {
        'Authorization': `Basic ${credentials}`,
        'Content-Type': 'application/json',
      }
    });

    const scToken = await scTokenResponse.json();

    console.log(scToken)
  }


  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        User Name: <input name="userName" value={loginRequest.userName} onChange={handleInputChange} />
        <br />
        Password: <input name="userPassword" value={loginRequest.userPassword} onChange={handleInputChange} />
        <br />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
