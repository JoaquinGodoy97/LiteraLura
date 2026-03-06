
### Functionality

1. **Look for books**  
   - Enter a book name to search.  
   - Displays a list of matching titles.  
   - You can select a book to save in your catalog.  
   - Duplicate entries are prevented (unique constraint on title).

2. **Show all the books you saved**  
   - Lists all saved books with details:  
     - Title  
     - Authors (name, birth year, death year)  
     - Languages  

3. **Look up books by title**  
   - Search saved books by their title.  
   - Displays matching entries with author and language information.

4. **Look up books if author was alive in a specific year**  
   - Enter a year.  
   - Shows all books whose authors were alive during that year.

5. **Look up books by language**  
   - Enter a language code (e.g., `eng`).  
   - Lists all saved books available in that language.

6. **Show saved authors**  
   - Displays all authors from saved books with their birth and death years.

0. **Exit**  
   - Closes the application.

---

## ⚙️ Technical Notes

- **Persistence**: Uses JPA/Hibernate for database operations.  
- **Error Handling**: Duplicate book titles are rejected with a warning.  
- **Logging**: Hibernate and HikariCP logs provide feedback on database operations.  
- **Exit**: Ensures proper shutdown of the `EntityManagerFactory` and connection pool.

---

## 📝 Example Usage

- Search for `book` → Select from multiple results like *The Dune Country* or *Sand dunes story*.  
- Show saved books → Lists titles like *Hamlet*, *Twilight*, *The Works of William Shakespeare*.  
- Query by year → Enter `${year}` → Returns *The Dune Country* and *Twilight*.  
- Query by language → Enter `${language}` → Returns all English books.  
- Show authors → Lists like Shakespeare, Earl H. Reed, Julia Frankau, etc.

---

## 🔚 Exit Codes

- `0` → Normal exit.  
---
