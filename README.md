# MCP Server Project

This project implements a Model Context Protocol (MCP) server and a static MCP client.  
The client sends a user query, the server chooses the appropriate method/tool, executes it, and returns the result.  
The setup is functional, extendable, and ready for deeper integration into larger systems such as your Personal Assistant AI.

---

## 🚀 Current Capabilities

- Fully working **MCP server** built with Spring + MCP documentation.
- Working **MCP client**:
    - Accepts user questions.
    - Sends them to the MCP server.
    - Automatically selects the correct tool/method.
    - Returns the processed response.
- End-to-end functional flow between client → server → tool → output.

---

## 📚 Resources Used During Development

- https://modelcontextprotocol.io/docs/getting-started/intro
- https://modelcontextprotocol.io/docs/develop/build-server#java
- https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html#_configuration_properties
- https://docs.spring.io/spring-ai/reference/index.html
- https://github.com/spring-projects/spring-ai-examples/blob/main/model-context-protocol/weather/manual-webflux-server/src/main/java/org/springframework/ai/mcp/sample/server/WeatherApiClient.java
- https://github.com/danvega/dvaas
- https://spring.io/blog/2025/09/16/spring-ai-mcp-intro-blog
- https://docs.spring.io/spring-ai/reference/api/mcp/mcp-streamable-http-server-boot-starter-docs.html
- https://www.youtube.com/watch?v=TSFkdlreRMQ
- https://platform.openai.com/docs/guides/tools-connectors-mcp
- https://docs.spring.io/spring-ai/reference/api/vectordbs/chroma.html#_configuration_properties
- https://docs.spring.io/spring-ai/reference/api/embeddings/google-genai-embeddings-text.html
- https://docs.spring.io/spring-ai/docs/current/api/org/springframework/ai/chroma/vectorstore/ChromaApi.html?utm_source=chatgpt.com
- https://docs.spring.io/spring-ai/docs/current/api/org/springframework/ai/vectorstore/VectorStore.html?utm_source=chatgpt.com


(And additional sources explored during implementation.)

---

## 🧭 Next Steps

### 1. Add Robust Backend Logic + Error Handling
- Validate input parameters before tool execution.
- Add structured error messages for both client and server.
- Improve tool selection logic and fallback behavior.
- Add safe defaults to avoid crashes or invalid responses.

### 2. Expand MCP Tools (More Methods)
Introduce more operations your MCP server can perform:
- Fetching external API data.
- Uploading/storing data.
- Editing existing server-side data.
- Multi-step operations.
- Categorized tools for clearer structure.

### 3. Connect MCP to Your Assistant AI
Integrate this MCP system into your existing Assistant AI project:
- Use MCP tools as context providers instead of (or alongside) the vector DB.
- Enable dynamic execution of tools by the AI (agent-style behavior).
- Allow the assistant to fetch, update, or store information via MCP.

This step transforms your assistant into a **real AI agent**, not just a RAG bot.

---

## 🔧 Optional Future Improvements

- Create a proper UI to interact with the MCP server.
- Add authentication (API tokens / JWT).
- Logging and observability for tool calls.
- Containerize the server (Docker).
- Add automated tests and CI/CD.
- Document each tool individually for future contributors.

---

## 📄 License

MIT License.

