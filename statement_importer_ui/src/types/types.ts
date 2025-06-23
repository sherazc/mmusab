export const emptyScToken = (): ScToken => ({
  token: "",
  userName: "",
  roles: "",
  createdDateTime: "",
  expiresDateTime: ""
});

export type ScToken = {
  token: string,
  userName: string,
  roles: string,
  createdDateTime: string,
  expiresDateTime: string
}

export type LoginRequest = {
  userName: string;
  userPassword: string;
}
